package com.system.service.impl;

import com.system.BaseTest;
import com.system.po.UserLogin;
import com.system.service.UserLoginService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class UserLoginServiceImplTest extends BaseTest{

    @Autowired
    private UserLoginService userLoginService;

    @Test
    public void save() throws Exception{
        UserLogin userLogin = new UserLogin();
        userLogin.setRole(1);
        userLogin.setUsername("testAccount");
        userLogin.setPassword("123");
        userLoginService.save(userLogin);
    }

    @Test
    public void findByName() throws Exception {
        UserLogin userLogin = userLoginService.findByName("admin");
        System.out.println(userLogin.toString());
    }

    @Test
    public void updateByName() throws Exception {
        UserLogin userLogin = new UserLogin();
        userLogin.setUserid(14);
        userLogin.setRole(2);
        userLogin.setUsername("testAccountUpdate");
        userLogin.setPassword("123");
        userLoginService.updateByName("1001", userLogin);
    }

    @Test
    public void removeByName() throws Exception{
        userLoginService.removeByName("testAccount");
    }


}
