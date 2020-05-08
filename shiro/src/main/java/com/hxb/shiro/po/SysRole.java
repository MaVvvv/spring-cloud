package com.hxb.shiro.po;

import java.util.List;

/**
 * 系统角色数据持久实体类
 *
 * @author Ma_wei
 * @since 2019-07-11 17:31
 */
public class SysRole {

    /** 角色编号*/
    private int id;

    /** 角色名称*/
    private String roleName;

    /** 所拥有权限*/
    private List<SysPermission> permissions;

    /** 所持有用户*/
    private List<SysUser> users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysPermission> permissions) {
        this.permissions = permissions;
    }

    public List<SysUser> getUsers() {
        return users;
    }

    public void setUsers(List<SysUser> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", permissions=" + permissions +
                ", users=" + users +
                '}';
    }
}
