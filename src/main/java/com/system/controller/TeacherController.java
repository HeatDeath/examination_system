package com.system.controller;

import com.github.pagehelper.PageInfo;
import com.system.po.Course;
import com.system.po.CourseCustom;
import com.system.po.SelectedCourseCustom;
import com.system.service.CourseService;
import com.system.service.SelectedCourseService;
import com.system.service.TeacherService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    // 分页行数
    private static final int ROWS = 2;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private SelectedCourseService selectedCourseService;

    // 显示我的课程
    @RequestMapping(value = "/showCourse", method = RequestMethod.GET)
    public ModelAndView showCourse(@RequestParam(required = false, defaultValue = "1") int page) throws Exception {
        // 获取登录用户的用户名
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        // 获取登录教师所教授的课程
        List<CourseCustom> courseCustomList = courseService.findByTeacherID(Integer.parseInt(username), page, ROWS);
        ModelAndView result = new ModelAndView("teacher/showCourse");

        // 将 courseCustomList 加入数据模型中
        result.addObject("pageInfo", new PageInfo<CourseCustom>(courseCustomList));
        result.addObject("page", page);
        result.addObject("rows", ROWS);
        return result;
    }

    // 显示成绩
    @RequestMapping(value = "/showCourseGrade", method = RequestMethod.GET)
    public String gradeCourse(@RequestParam("id") Integer courseID, Model model) throws Exception {

        List<SelectedCourseCustom> list = selectedCourseService.findByCourseID(courseID);
        model.addAttribute("selectedCourseList", list);
        return "teacher/showGrade";
    }

    // 打分
    @RequestMapping(value = "/mark", method = {RequestMethod.GET})
    public String markUI(SelectedCourseCustom scc, Model model) throws Exception {

        SelectedCourseCustom selectedCourseCustom = selectedCourseService.findOne(scc);

        model.addAttribute("selectedCourse", selectedCourseCustom);

        return "teacher/mark";
    }

    // 打分
    @RequestMapping(value = "/mark", method = {RequestMethod.POST})
    public String mark(SelectedCourseCustom scc) throws Exception {

        selectedCourseService.updateOne(scc);

        return "redirect:/teacher/gradeCourse?id="+scc.getCourseid();
    }

    //修改密码
    @RequestMapping(value = "/passwordRest")
    public String passwordRest() throws Exception {
        return "teacher/passwordRest";
    }
}
