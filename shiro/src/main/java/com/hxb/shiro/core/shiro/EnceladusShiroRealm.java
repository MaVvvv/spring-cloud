package com.hxb.shiro.core.shiro;

import com.hxb.shiro.po.SysPermission;
import com.hxb.shiro.po.SysRole;
import com.hxb.shiro.po.SysUser;
import com.hxb.shiro.service.ISysUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-07-11 18:30
 */
public class EnceladusShiroRealm extends AuthorizingRealm {

    /** 日志*/
    private static final Logger log = LoggerFactory.getLogger(EnceladusShiroRealm.class);

    /** 系统用户信息业务处理实例*/
    @Autowired
    private ISysUserService sysUserServiceImpl;

    /**
     * 权限信息认证
     *
     * Retrieves the AuthorizationInfo for the given principals from the underlying data store.  When returning
     * an instance from this method, you might want to consider using an instance of
     * {@link SimpleAuthorizationInfo SimpleAuthorizationInfo}, as it is suitable in most cases.
     *
     * @param principals the primary identifying principals of the AuthorizationInfo that should be retrieved.
     * @return the AuthorizationInfo associated with this principals.
     * @see SimpleAuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.debug("进入EnceladusShiroRealm.doGetAuthorizationInfo()方法...");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 获取用户名称
        String userName = (String)principals.getPrimaryPrincipal();
        // 获取用户角色信息
        SysUser user = this.getSysUserInfoByName(userName);
        // 角色
        for (SysRole role : user.getRoles()) {
            authorizationInfo.addRole(role.getRoleName());
        }
        // 权限
        for (SysPermission permission : user.getPermissions()) {
            authorizationInfo.addStringPermission(permission.getName());
        }
        return authorizationInfo;
    }

    /**
     * 登陆信息认证
     *
     * Retrieves authentication data from an implementation-specific datasource (RDBMS, LDAP, etc) for the given
     * authentication token.
     * <p/>
     * For most datasources, this means just 'pulling' authentication data for an associated subject/user and nothing
     * more and letting Shiro do the rest.  But in some systems, this method could actually perform EIS specific
     * log-in logic in addition to just retrieving data - it is up to the Realm implementation.
     * <p/>
     * A {@code null} return value means that no account could be associated with the specified token.
     *
     * @param token the authentication token containing the user's principal and credentials.
     * @return an {@link AuthenticationInfo} object containing account data resulting from the
     * authentication ONLY if the lookup is successful (i.e. account exists and is valid, etc.)
     * @throws AuthenticationException if there is an error acquiring data or performing
     *                                 realm-specific authentication logic for the specified <tt>token</tt>
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.debug("进入EnceladusShiroRealm.doGetAuthenticationInfo()方法...");
        String userName = (String)token.getPrincipal();
        SysUser user = sysUserServiceImpl.getUserInfoByName(userName);
        if (user == null) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getName(), user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()), getName());
        return authenticationInfo;
    }

    /**
     * 通过用户名称查询用户角色，权限信息
     *
     * @param userName
     * @return SysUser
     * @author Ma_wei
     * @since 2019/7/12
     */
    private SysUser getSysUserInfoByName (String userName) {
        log.debug("进入EnceladusShiroRealm.getSysUserInfoByName()方法...");
        log.info("当前登陆用户名称为：{}",userName);
        SysUser user = new SysUser();
        user.setName(userName);
        List<SysRole> userRoles = sysUserServiceImpl.getUserRolesByUserName(userName);
        user.setRoles(userRoles);
        List<SysPermission> userPermission = sysUserServiceImpl.getUserPermissionsByRole(userRoles);
        user.setPermissions(userPermission);
        log.info("用户角色权限信息为：{}",user.toString());
        return user;
    }
}
