package com.system.controller;

import com.alibaba.fastjson.JSON;
import com.system.po.UserLogin;
import com.system.service.UserLoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
public class ResetCurrentPasswordController {
    @Autowired
    private UserLoginService userLoginService;


    @ResponseBody
    @RequestMapping(value = "/passwordReset", method = RequestMethod.POST)
    public String doPasswordReset(@RequestParam("oldPassword") String oldPassword,
                                @RequestParam("password1") String newPassword) throws Exception{
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        UserLogin userLogin = userLoginService.findByName(username);
        Map<String , Object> resultMap = new HashMap<>();

        if(!oldPassword.equals(userLogin.getPassword())){
            resultMap.put("msg", "旧密码错误！");
            resultMap.put("page_url", "");
        }else {
            userLogin.setPassword(newPassword);
            userLoginService.updateByName(username, userLogin);
            resultMap.put("msg", "修改密码成功，请重新登陆！");
            resultMap.put("page_url", "/logout");
        }
        return JSON.toJSONString(resultMap);
    }


}
