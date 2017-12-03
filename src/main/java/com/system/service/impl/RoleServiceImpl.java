package com.system.service.impl;

import com.system.dao.RoleMapper;
import com.system.pojo.Role;
import com.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role findById(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }
}
