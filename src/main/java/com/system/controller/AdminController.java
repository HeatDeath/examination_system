package com.system.controller;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.system.po.Course;
import com.system.po.CourseCustom;
import com.system.po.CourseExample;
import com.system.service.CollegeService;
import com.system.service.CourseService;
import com.system.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@SessionAttributes({"courseCount"})
@RequestMapping("/admin")
public class AdminController {

    private static final  int ROWS = 3;

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
                                   @RequestParam(required = false, defaultValue = "1") int page) throws Exception{
        ModelAndView result = new ModelAndView("/admin/showCourse");
        List<Course> courseList = courseService.selectCourseByName(course, page, ROWS);
        // 获取课程数量
        Long courseCount = courseService.getCountCourse();
        result.addObject("pageInfo", new PageInfo<Course>(courseList));
        result.addObject("page", page);
        result.addObject("rows", ROWS);
        result.addObject("queryParam", course);
        result.addObject("courseCount", courseCount);

        return result;
    }

//    // 显示课程信息
//    @ResponseBody
//    @RequestMapping(value = {"/searchCourse",
//                             "/searchCourse/page/{page}",
//                             "/searchCourse/{coursename}/page/{page}"})
//    public String searchCourse(@PathVariable String coursename, @PathVariable int page) throws Exception{
//
//        Course course = new Course();
//        course.setCoursename(coursename);
//
//        List<Course> courseList = courseService.selectCourseByName(course, page, ROWS);
//        // 获取课程数量
//        Long courseCount = courseService.getCountCourse();
//
//        Map<String, Object> resultMap = new HashMap<>();
//        resultMap.put("pageInfo", new PageInfo<Course>(courseList));
//        resultMap.put("page", page);
//        resultMap.put("rows", ROWS);
//        resultMap.put("queryParam", course);
//        resultMap.put("courseCount", courseCount);
//
//        return JSON.toJSONString(resultMap);
//    }

    // 添加课程
    @RequestMapping(value = "/addCourse", method = RequestMethod.GET)
    public ModelAndView addCourse() throws Exception{
        ModelAndView result = new ModelAndView("/admin/addCourse");
        result.addObject("teacherList", teacherService.findAll());
        result.addObject("collegeList", collegeService.finAll());
        return result;
    }

    // 添加课程表单处理
    @ResponseBody
    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
    public String doAddCourse(CourseCustom courseCustom) throws Exception{

        Boolean result = courseService.save(courseCustom);
        Map<String, Object> resultMap = new HashMap<>();

        if (result){
            resultMap.put("msg", "success");
        }else {
            resultMap.put("msg", "fail");
        }
        return JSON.toJSONString(resultMap);
    }





}
