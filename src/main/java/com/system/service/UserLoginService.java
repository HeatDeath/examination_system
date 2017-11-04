package com.system.service;

import com.system.po.UserLogin;

public interface UserLoginService {

    // 通过用户名查找 UserLogin 对象
    UserLogin findByName(String name);

    // 保存用户登录信息
    void save(UserLogin userLogin);

    // 根据用户名删除对应的 UserLogin
    void removeByName(String name);

    // 根据用户名更新 UserLogin
    void updateByName(String name, UserLogin userLogin);
}
