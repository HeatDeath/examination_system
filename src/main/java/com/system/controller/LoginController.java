package com.system.controller;

import com.system.pojo.UserLogin;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.apache.shiro.subject.Subject;


@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(method = RequestMethod.GET)
    public String getLogin() throws Exception{
        return "/login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String handleLoginForm(UserLogin userLogin) throws Exception{
        // Shiro 实现登录
        UsernamePasswordToken token = new UsernamePasswordToken(userLogin.getUsername(),
                                                                userLogin.getPassword());
        // 获取当前登录的 Subject
        Subject role = SecurityUtils.getSubject();

        //如果获取不到用户名就是登录失败，但登录失败的话，会直接抛出异常
        role.login(token);

        if (role.hasRole("admin")) {
            return "redirect:/admin/showCourse";
        }else if (role.hasRole("teacher")) {
            return "redirect:/teacher/showCourse";
        }else if (role.hasRole("student")) {
            return "redirect:/student/showCourse";
        }

        return "/login";
    }


}
