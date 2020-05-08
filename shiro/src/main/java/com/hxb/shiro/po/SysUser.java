package com.hxb.shiro.po;

import java.util.List;

/**
 * 系统用户管理数据持久实体类
 *
 * @author Ma_wei
 * @since 2019-07-11 17:29
 */
public class SysUser {

    /** 用户编号*/
    private int id;

    /** 用户名称*/
    private String name;

    /** 密码*/
    private String password;

    /** 离散因子*/
    private String salt;

    /** 权限*/
    private List<SysPermission> permissions;

    /** 角色*/
    private List<SysRole> roles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    public List<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysPermission> permissions) {
        this.permissions = permissions;
    }

    public String getCredentialsSalt() {
        return name + salt + salt;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", permissions=" + permissions +
                ", roles=" + roles +
                '}';
    }
}
