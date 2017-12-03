package com.system.interceptor;

import com.system.pojo.SelectedCourseCustom;
import com.system.service.CourseService;
import com.system.service.SelectedCourseService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentInterceptor implements HandlerInterceptor {
    @Autowired
    private CourseService courseService;

    @Autowired
    private SelectedCourseService selectedCourseService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 获取课程数量
        Long courseCount = courseService.getCountCourse();

        // 获取用户id
        Subject subject = SecurityUtils.getSubject();
        Integer userID = Integer.parseInt((String)subject.getPrincipal());

        // 通过 userID 获取该学生已选课程
        List<SelectedCourseCustom> list = selectedCourseService.findByStudentID(userID);

        // 学生选课数量统计
        int studentSelectedCourseCount = list.size();

        // 学生完成课程数量统计
        int studentFinishCourseCount = 0;
        for (SelectedCourseCustom selectedCourseCustom:list) {
            if (selectedCourseCustom.getMark() != null){
                studentFinishCourseCount++;
            }
        }

        Map<String, Object> countMap = new HashMap<>();
        countMap.put("courseCount", courseCount);
        countMap.put("studentSelectedCourseCount", studentSelectedCourseCount);
        countMap.put("studentFinishCourseCount", studentFinishCourseCount);

        httpServletRequest.getSession().setAttribute("countMap", countMap);
        httpServletRequest.getSession().setAttribute("userID", userID);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
