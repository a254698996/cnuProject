package web.handler;

import java.io.Serializable;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import web.content.Constant;
import web.entity.User;
import web.service.IUserService;

//  http://jinnianshilongnian.iteye.com/blog/2022468
@Component("myRealm")
public class UserRealm extends AuthorizingRealm {

	@Autowired
	@Lazy
	IUserService<User, Serializable> userService;

	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		User user = userService.getUserRolesAndPermissions(userName);
		authorizationInfo.setRoles(user.getRoleSet());
		authorizationInfo.setStringPermissions(user.getPermissionSet());
		return authorizationInfo;
	}

	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) 
			throws AuthenticationException {
		UsernamePasswordToken realToken = (UsernamePasswordToken) token;
		User user = new User();
		user.setUsername(realToken.getUsername());
		user.setPassword(String.valueOf(realToken.getPassword()));

		User returnUser = userService.queryBeanByHql(user);
		if (returnUser == null) {
			throw new UnknownAccountException();// 没找到帐号
		}

		if (Constant.State.STATE_NORMAL != returnUser.getState()) {
			throw new LockedAccountException(); // 帐号锁定
		}

		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(), // 用户名
				user.getPassword(), // 密码
				getName() // realm name
		);
		
		return authenticationInfo;
	}
}