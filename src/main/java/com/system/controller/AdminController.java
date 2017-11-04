package com.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.sun.org.apache.xpath.internal.operations.String;
import com.system.po.Course;
import com.system.po.CourseCustom;
import com.system.po.CourseExample;
import com.system.service.CollegeService;
import com.system.service.CourseService;
import com.system.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@SessionAttributes({"courseCount"})
@RequestMapping("/admin")
public class AdminController {


//    @Autowired
//    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CollegeService collegeService;
//
//    @Autowired
//    private UserloginService userloginService;

    // 显示课程信息
    @RequestMapping(value = {"/showCourse", "/searchCourse"})
    public ModelAndView showCourse(Course course,
                                   @RequestParam(required = false, defaultValue = "1") int page,
                                   @RequestParam(required = false, defaultValue = "2") int rows) throws Exception{
        ModelAndView result = new ModelAndView("/admin/showCourse");
        List<Course> courseList = courseService.selectCourseByName(course, page, rows);
        // 获取课程数量
        Long courseCount = courseService.getCountCourse();
        result.addObject("pageInfo", new PageInfo<Course>(courseList));
        result.addObject("page", page);
        result.addObject("rows", rows);
        result.addObject("queryParam", course);
        result.addObject("courseCount", courseCount);
        return result;
    }

    // 添加课程
    @RequestMapping(value = "/addCourse", method = RequestMethod.GET)
    public ModelAndView addCourse() throws Exception{
        ModelAndView result = new ModelAndView("/admin/addCourse");
        result.addObject("teacherList", teacherService.findAll());
        result.addObject("collegeList", collegeService.finAll());
        return result;
    }

    // 添加课程表单处理
    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
    public ModelAndView doAddCourse(CourseCustom courseCustom) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        Boolean result = courseService.save(courseCustom);
        if (result){
            modelAndView.setViewName("redirect:/admin/showCourse");
            modelAndView.addObject("message", "成功添加课程！");
        }else {
            modelAndView.setViewName("redirect:/admin/addCourse");
            modelAndView.addObject("message", "课程号重复！添加失败！");
        }
        return modelAndView;
    }





}
