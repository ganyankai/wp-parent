package cn.itcast.shiro.realm;

import cn.itcast.shiro.domain.Permission;
import cn.itcast.shiro.domain.Role;
import cn.itcast.shiro.domain.User;
import cn.itcast.shiro.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/*
 *  自定义的realm
 */
public class CustomRealm extends AuthorizingRealm {

    public void setName(String name){
        super.setName("custom");
    }

    @Autowired
    private UserService userService;

    /*
     *
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //1.获取已认证的用户数据
        User user = (User)principalCollection.getPrimaryPrincipal();  //得到唯一的安全数据

        //2.根据用户数据获取用户的权限信息(所有角色,所有权限)
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Set<String> roles =  new HashSet<>();   //所有角色
        Set<String> perms = new HashSet<>();    //所有权限
        for (Role role:user.getRoles()){
            roles.add(role.getName());
            for(Permission perm:role.getPermissions()){
                perms.add(perm.getCode());
            }
        }

        info.setStringPermissions(perms);
        info.setRoles(roles);
        return info;
    }

    /**
     *
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1.获取登录名密码(token)
        UsernamePasswordToken upToken = (UsernamePasswordToken)authenticationToken;
        //2.根据用户名查询数据库
        String username= upToken.getUsername();
        String password = new String(upToken.getPassword());
        //3.判断用户名是否存在或者密码是否一致
        User user = userService.findByName(username);
        if (user != null && user.getPassword().equals(password)){
            //4.如果一致,返回安全数据
            //构造方法  安全数据 密码  realm域名
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
            return info;
        }

        //5.不一致,返回null(抛出异常)
        return null;
    }
}
