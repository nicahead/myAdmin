package me.nic.shiro.realm;

import com.baomidou.mybatisplus.extension.api.R;
import com.shu.emergency.api.common.dto.AuthUser;
import com.shu.emergency.api.modules.sys.entity.Menu;
import com.shu.emergency.api.modules.sys.entity.Role;
import com.shu.emergency.api.modules.sys.entity.User;
import com.shu.emergency.api.modules.sys.service.impl.MenuServiceImpl;
import com.shu.emergency.api.modules.sys.service.impl.RoleServiceImpl;
import com.shu.emergency.api.modules.sys.service.impl.UserServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author nicahead@gmail.com
 * @date 2020/2/25 10:44
 */
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RoleServiceImpl roleService;

    @Override
    public void setName(String name) {
        super.setName("AuthRealm");
    }

    //授权方法，获取安全数据，构造权限信息
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //1.获取安全数据
        AuthUser authUser = (AuthUser)principalCollection.getPrimaryPrincipal();
        //2.获取权限信息
        Set<String> perms = (Set<String>)authUser.getRoles().get("apis");
        perms.addAll((Set<String>)authUser.getRoles().get("menus"));
        perms.addAll((Set<String>)authUser.getRoles().get("points"));
        //3.构造权限数据，返回值
        SimpleAuthorizationInfo info = new  SimpleAuthorizationInfo();
        info.setStringPermissions(perms);
        return info;
    }

    //认证方法，根据用户名密码进行登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1.获取用户名和密码
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String username = upToken.getUsername();
        String password = new String( upToken.getPassword());
        //2.根据用户名查询用户
        User user = userService.getUserByUsername(username);
        //3.判断用户是否存在，用户密码是否和输入密码一致
        if(user != null && user.getPassword().equals(password)) {
            //4.构造安全数据并返回（安全数据：用户基本数据，权限信息 AuthUser）
            List<Menu> permissions = new ArrayList<>();
            Set<Role> roles = user.getRoles(); //该用户的角色列表
            if (roles.size() > 0){
                // 遍历角色
                for (Role role : roles){
                    Role roleById = roleService.getRoleById(role.getId());
                    permissions.addAll(roleById.getMenus());
                }
            }
            AuthUser authUser = new AuthUser(user,permissions);
            //构造方法：安全数据，密码，realm域名
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(authUser,user.getPassword(),this.getName());
            return info;
        }
        //返回null，会抛出异常，标识用户名和密码不匹配
        return null;
    }
}
