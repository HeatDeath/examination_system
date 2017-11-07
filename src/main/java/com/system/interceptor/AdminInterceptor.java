package com.system.interceptor;

import com.system.service.CourseService;
import com.system.service.StudentService;
import com.system.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class AdminInterceptor implements HandlerInterceptor {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 获取课程数量
        Long courseCount = courseService.getCountCourse();

        // 获取学生数量
        Long studentCount = studentService.getCountStudent();

        // 获取教师数量
        Long teacherCount = teacherService.getCountTeacher();

        Map<String, Object> countMap = new HashMap<>();
        countMap.put("courseCount", courseCount);
        countMap.put("studentCount", studentCount);
        countMap.put("teacherCount", teacherCount);

        httpServletRequest.getSession().setAttribute("countMap", countMap);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
