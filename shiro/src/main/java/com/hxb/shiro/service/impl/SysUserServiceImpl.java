package com.hxb.shiro.service.impl;

import com.hxb.shiro.domain.ISysUserDao;
import com.hxb.shiro.po.SysPermission;
import com.hxb.shiro.po.SysRole;
import com.hxb.shiro.po.SysUser;
import com.hxb.shiro.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 系统用户信息业务逻辑处理接口实现类
 *
 * @author Ma_wei
 * @since 2019-07-11 18:32
 */
@Service
public class SysUserServiceImpl implements ISysUserService {

    /** 系统用户数据库处理实例*/
    @Autowired
    private ISysUserDao iSysUserDao;

    /**
     * 通过用户名称获取用户角色信息
     *
     * @param userName
     * @return List<SysRole>
     * @author Ma_wei
     * @since 2019/7/12
     */
    @Override
    public List<SysRole> getUserRolesByUserName(@NotNull @NotEmpty String userName) {
        return iSysUserDao.getUserRolesByUserName(userName);
    }

    /**
     * 通过角色查询权限信息
     *
     * @param roles
     * @return List<SysPermission>
     * @author Ma_wei
     * @since 2019/7/12
     */
    @Override
    public List<SysPermission> getUserPermissionsByRole(@NotEmpty List<SysRole> roles) {
        return iSysUserDao.getUserPermissionsByRole(roles);
    }

    /**
     * 根据用户名称获取用户信息
     *
     * @param userName
     * @return SysUser
     * @author Ma_wei
     * @since 2019/7/12
     */
    @Override
    public SysUser getUserInfoByName(@NotNull @NotEmpty String userName) {
        return iSysUserDao.getUserInfoByName(userName);
    }
}
