package com.system.service;

import com.system.pojo.Role;

public interface RoleService {
    // 通过 id 查找 role
    Role findById(Integer id);
}
