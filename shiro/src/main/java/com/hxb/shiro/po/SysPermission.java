package com.hxb.shiro.po;

import java.util.List;

/**
 * 系统权限数据持久实体类
 *
 * @author Ma_wei
 * @since 2019-07-11 17:32
 */
public class SysPermission {

    /** 权限编号*/
    private int id;

    /** 权限名称*/
    private String name;

    /** 权限所属角色*/
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

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "SysPermission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", roles=" + roles +
                '}';
    }
}
