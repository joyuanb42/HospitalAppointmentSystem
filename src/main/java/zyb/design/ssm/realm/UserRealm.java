package zyb.design.ssm.realm;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import zyb.design.ssm.entity.Role;
import zyb.design.ssm.entity.TreeNode;
import zyb.design.ssm.entity.User;
import zyb.design.ssm.entity.UserInfo;
import zyb.design.ssm.service.RoleService;
import zyb.design.ssm.service.UserService;
import zyb.design.ssm.utils.MD5AndSalt;

import java.util.List;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principalCollection.getPrimaryPrincipal();
        String username = user.getUsername();
        // 根据用户名获取用户的角色和权限列表
        UserInfo userInfo = userService.findByUserName(username);
        List<Integer> roles = userInfo.getRoles();
        List<TreeNode> permissions = userInfo.getTreeNodes();
        // 一次性获取所有角色
        List<Role> roleList = roleService.listByIds(roles);
        // 将角色列表添加到AuthorizationInfo中
        for (Role role : roleList) {
            authorizationInfo.addRole(role.getRoleName());
        }
        // 将权限列表添加到AuthorizationInfo中
        for (TreeNode permission : permissions) {
            authorizationInfo.addStringPermission(permission.getPermission());
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证");
        // 将AuthenticationToken转换为UsernamePasswordToken
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        // 获取用户名和密码
        String username = usernamePasswordToken.getUsername();
        String password = new String(usernamePasswordToken.getPassword());
        // 根据用户名获取用户信息
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        User user = userService.getOne(wrapper);
        if (user == null) {
            throw new UnknownAccountException("用户名不存在");
        }
        // 对密码进行加密，然后与数据库中的密码比对
        String salt = user.getSalt();
        String encryptedPassword = MD5AndSalt.encryptPassword(password, salt);
        if (!encryptedPassword.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("密码错误");
        }
        // 构造AuthenticationInfo对象并返回
        return new SimpleAuthenticationInfo(user, password, ByteSource.Util.bytes(salt), getName());
    }
}
