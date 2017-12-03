package com.system.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.system.pojo.CourseCustom;
import com.system.pojo.SelectedCourseCustom;
import com.system.service.CourseService;
import com.system.service.SelectedCourseService;
import com.system.service.StudentService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/student")
public class StudentController {
    // 分页行数
    private static final int ROWS = 2;
//    private Integer userID = Integer.parseInt((String) SecurityUtils.getSubject().getPrincipal());

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SelectedCourseService selectedCourseService;

    // 展示所有课程
    @RequestMapping(value = "/showCourse")
    public ModelAndView showCourse(@RequestParam(required = false, defaultValue = "1") int page) throws Exception {
        List<CourseCustom> courseCustomList = courseService.selectAll(page, ROWS);
        ModelAndView result = new ModelAndView("/student/showCourse");
        result.addObject("pageInfo", new PageInfo<CourseCustom>(courseCustomList));
        result.addObject("page", page);
        result.addObject("rows", ROWS);
        return result;
    }

    // 处理选课操作
    @ResponseBody
    @RequestMapping(value = "/doSelectedCourse")
    public String doSelectedCourse(HttpServletRequest httpServletRequest,
                                   @RequestParam("id") Integer courseId) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        SelectedCourseCustom selectedCourseCustom = new SelectedCourseCustom();
        selectedCourseCustom.setCourseid(courseId);
        selectedCourseCustom.setStudentid((Integer) httpServletRequest.getSession().getAttribute("userID"));
        boolean flag = selectedCourseService.save(selectedCourseCustom);
        if (flag) {
            resultMap.put("msg", "success");
            resultMap.put("page_url", "/student/showSelectedCourse");
        } else {
            resultMap.put("msg", "fail");
            resultMap.put("page_url", "/student/showCourse");
        }
        return JSON.toJSONString(resultMap);
    }

    // 展示已选课程
    @RequestMapping(value = "/showSelectedCourse")
    public ModelAndView selectedCourse() throws Exception {
        // 获取 userID
        Subject subject = SecurityUtils.getSubject();
        Integer userID = Integer.parseInt((String) subject.getPrincipal());
        List<SelectedCourseCustom> selectedCourseCustoms = selectedCourseService.findByStudentID(userID);
        ModelAndView result = new ModelAndView("/student/showSelectedCourse");
        result.addObject("selectedCourseList", selectedCourseCustoms);
        return result;
    }

    // 退课操作
    @ResponseBody
    @RequestMapping(value = "/dropCourse")
    public String dropCourse(@RequestParam("id") Integer courseID) throws Exception {
        // 获取 userID
        Subject subject = SecurityUtils.getSubject();
        Integer userID = Integer.parseInt((String) subject.getPrincipal());

        SelectedCourseCustom selectedCourseCustom = new SelectedCourseCustom();
        selectedCourseCustom.setCourseid(courseID);
        selectedCourseCustom.setStudentid(userID);

        Map<String, Object> resultMap = new HashMap<>();
        try {
            selectedCourseService.remove(selectedCourseCustom);
            resultMap.put("msg", "success");
        } catch (Exception e) {
            resultMap.put("msg", "fail");
        }
        return JSON.toJSONString(resultMap);
    }

    // 展示已修课程
    @RequestMapping(value = "/finishedCourse")
    public ModelAndView finishCourse() throws Exception {
        // 获取 userID
        Subject subject = SecurityUtils.getSubject();
        Integer userID = Integer.parseInt((String) subject.getPrincipal());
        List<SelectedCourseCustom> selectedCourseCustoms = selectedCourseService.findByStudentID(userID);

        // 筛选出已经获得成绩（修完）的课程
        List<SelectedCourseCustom> finishedCourseList = new ArrayList<>();
        for (SelectedCourseCustom scc : selectedCourseCustoms) {
            if (scc.getMark() != null) {
                finishedCourseList.add(scc);
            }
        }

        ModelAndView result = new ModelAndView("/student/finishedCourse");
        result.addObject("finishedCourseList", finishedCourseList);
        return result;
    }

    // 重置学生账户密码 页面跳转
    @RequestMapping(value = "/passwordReset", method = RequestMethod.GET)
    public String passwordReset() throws Exception {
        return "/student/passwordReset";
    }
}
