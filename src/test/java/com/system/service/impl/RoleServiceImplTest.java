package com.system.service.impl;

import com.system.BaseTest;
import com.system.po.Role;
import com.system.service.RoleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class RoleServiceImplTest extends BaseTest{

    @Autowired
    private RoleService roleService;

    @Test
    public void findById() throws Exception{
        Role role = roleService.findById(0);
        System.out.println(role.toString());
    }
}
