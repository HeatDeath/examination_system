package com.system.realm;

import com.system.pojo.Role;
import com.system.pojo.UserLogin;
import com.system.service.RoleService;
import com.system.service.UserLoginService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * 自定义的指定Shiro验证用户登录的类
 */

@Component
public class LoginRealm extends AuthorizingRealm{

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private RoleService roleService;

    /**
     * 为当前登录的Subject授予角色和权限
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //获取当前登录的用户名
        String username = (String) getAvailablePrincipal(principalCollection);

        Role role = null;

        try {
            //从数据库中获取当前登录用户的详细信息
            UserLogin userlogin = userLoginService.findByName(username);

            //获取角色对象
            role = roleService.findById(userlogin.getRole());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 通过用户名从数据库获取权限/角色信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> r = new HashSet<>();
        if (role != null) {
            r.add(role.getRolename());
            // 在返回的 AuthorizationInfo 对象 info 中设置 role 信息
            info.setRoles(r);
        }

        return info;
    }

    /**
     * 验证当前登录的Subject
     * 该方法的调用时机为LoginController.login()方法中执行Subject.login()时
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //用户名
        String username = (String) token.getPrincipal();
        //密码
        String password = new String((char[])token.getCredentials());

        UserLogin userlogin = null;
        try {
            userlogin = userLoginService.findByName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (userlogin == null) {
            //没有该用户名
            throw new UnknownAccountException();
        } else if (!password.equals(userlogin.getPassword())) {
            //密码错误
            throw new IncorrectCredentialsException();
        }

        //身份验证通过,返回一个身份信息
        AuthenticationInfo aInfo = new SimpleAuthenticationInfo(username,password,getName());

        return aInfo;
    }
}
