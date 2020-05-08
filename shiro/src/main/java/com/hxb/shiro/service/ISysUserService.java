package com.hxb.shiro.service;

import com.hxb.shiro.po.SysPermission;
import com.hxb.shiro.po.SysRole;
import com.hxb.shiro.po.SysUser;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 系统用户业务逻辑处理接口
 *
 * @author Ma_wei
 * @since 2019-07-11 18:32
 */
public interface ISysUserService {

    /**
     * 通过用户名称获取用户角色信息
     *
     * @param userName
     * @return List<SysRole>
     * @author Ma_wei
     * @since 2019/7/12
     */
    List<SysRole> getUserRolesByUserName(@NotNull @NotEmpty String userName);

    /**
     * 通过角色查询权限信息
     *
     * @param roles
     * @return List<SysPermission>
     * @author Ma_wei
     * @since 2019/7/12
     */
    List<SysPermission> getUserPermissionsByRole (@NotEmpty List<SysRole> roles );

    /**
     * 根据用户名称获取用户信息
     *
     * @param userName
     * @return SysUser
     * @author Ma_wei
     * @since 2019/7/12
     */
    SysUser getUserInfoByName (@NotNull @NotEmpty String userName);
}
