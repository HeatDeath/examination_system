package com.system.controller;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.system.po.*;
import com.system.service.CollegeService;
import com.system.service.CourseService;
import com.system.service.StudentService;
import com.system.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
//@SessionAttributes({"courseCount", "studentCount"})
@RequestMapping("/admin")
public class AdminController {

    private static final  int ROWS = 2;

    @Autowired
    private StudentService studentService;

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

        result.addObject("pageInfo", new PageInfo<Course>(courseList));
        result.addObject("page", page);
        result.addObject("rows", ROWS);
        result.addObject("queryParam", course);

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

    // 添加课程 页面跳转
    @RequestMapping(value = "/addCourse", method = RequestMethod.GET)
    public ModelAndView addCourse() throws Exception{
        ModelAndView result = new ModelAndView("/admin/addCourse");
        result.addObject("teacherList", teacherService.findAll());
        result.addObject("collegeList", collegeService.finAll());
        return result;
    }

    // 添加课程 表单处理
    @ResponseBody
    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
    public String doAddCourse(CourseCustom courseCustom) throws Exception{

        Boolean result = courseService.save(courseCustom);
        Map<String, Object> resultMap = new HashMap<>();

        if (result){
            resultMap.put("msg", "success");
            resultMap.put("page_url", "/admin/showCourse");
        }else {
            resultMap.put("msg", "fail");
            resultMap.put("page_url", "/admin/addCourse");

        }
        return JSON.toJSONString(resultMap);
    }

    // 编辑课程信息 页面跳转
    @RequestMapping(value = "/editCourse", method = RequestMethod.GET)
    public ModelAndView editCourse(@RequestParam("id") int courseId) throws Exception{
//        Map<String, Object> resultMap = new HashMap<>();
        CourseCustom courseCustom = courseService.findById(courseId);
//        if (courseCustom == null){
//            resultMap.put("msg", "请输入正确的课程号！");
//            return JSON.toJSONString(resultMap);
//        }
        List<Teacher> teacherList = teacherService.findAll();
        List<College> collegeList = collegeService.finAll();
        ModelAndView result = new ModelAndView();
        result.setViewName("/admin/editCourse");
        result.addObject("teacherList", teacherList);
        result.addObject("collegeList", collegeList);
        result.addObject("course", courseCustom);
//        resultMap.put("teacherList", teacherList);
//        resultMap.put("collegeList", collegeList);
        return result;
    }

    // 编辑课程信息 表单处理
    @ResponseBody
    @RequestMapping(value = "/editCourse", method = RequestMethod.POST)
    public String doEditCourse(Course course) throws Exception{
        Map<String, Object> resultMap = new HashMap<>();

        // 尝试 更新 课程信息
        try {
            courseService.updateByPrimaryKey(course);
            resultMap.put("msg", "success");
            resultMap.put("page_url", "/admin/showCourse");

            // 更新失败的话，alert 提示信息，返回课程编辑页面
        }catch (Exception e){
            resultMap.put("msg", "fail");
            resultMap.put("page_url", "/admin/editCourse?id=" + course.getCourseid());
        }

        // 返回 将 resultMap 转换后的 JSON 字符串
        return JSON.toJSONString(resultMap);
    }

    // 删除课程
    @ResponseBody
    @RequestMapping(value = "/removeCourse", method = RequestMethod.GET)
    public String removeCourse(@RequestParam("id") int courseId) throws Exception{
        Map<String, Object> resultMap = new HashMap<>();

        // 尝试 删除 课程信息
        // 删除成功的话，alert 提示 成功信息
        try {
            courseService.removeById(courseId);
            resultMap.put("msg", "success");

            // 删除失败的话，alert 提示 失败信息
        }catch (Exception e){
            resultMap.put("msg", "fail");
        }

        // 返回 将 resultMap 转换后的 JSON 字符串
        return JSON.toJSONString(resultMap);
    }

    // -----------------------------------------------------------------------

    // 显示学生信息
    @RequestMapping(value = "/showStudent")
    public ModelAndView showStudent(Student student,
                                    @RequestParam(required = false, defaultValue = "1") int page) throws Exception {
        ModelAndView result = new ModelAndView("/admin/showStudent");
        List<StudentCustom> studentList = studentService.findByName(student, page, ROWS);

        result.addObject("pageInfo", new PageInfo<StudentCustom>(studentList));
        result.addObject("page", page);
        result.addObject("rows", ROWS);
        result.addObject("queryParam", student);

        return result;
    }
}
