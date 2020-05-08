package com.hxb.shiro.domain;

import com.hxb.shiro.po.SysPermission;
import com.hxb.shiro.po.SysRole;
import com.hxb.shiro.po.SysUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-07-11 17:36
 */
@Mapper
public interface ISysUserDao {

    /** 日志*/
    Logger log = LoggerFactory.getLogger(ISysUserDao.class);

    /** 用户表*/
    String SYS_USER_TABLENAME = "sys_user";

    /** 角色表*/
    String SYS_ROLE_TABLENAME = "sys_role";

    /** 权限表*/
    String SYS_PERMISSION_TABLENAME = "sys_permission";

    /** 用户-角色表*/
    String SYS_USER_ROLE_TABLENAME = "sys_user_role";

    /** 角色-权限表*/
    String SYS_ROLE_PERMISSION_TABLENAME = "sys_role_permission";

    /**
     * 通过用户名称获取用户角色信息
     *
     * @param userName
     * @return List<SysRole>
     * @author Ma_wei
     * @since 2019/7/12
     */
    @Select("SELECT x.id, x.role_name FROM "+ SYS_ROLE_TABLENAME +" x " +
            "RIGHT JOIN " +
                "( SELECT r.role_id FROM " + SYS_USER_ROLE_TABLENAME + " r " +
                "LEFT JOIN " +
                    "( SELECT id FROM " + SYS_USER_TABLENAME +"  u WHERE u. NAME = '#{name}' ) z " +
                "ON r.id = z.id ) y " +
            "ON y.role_id = x.id)")
    @Results(value = {
            @Result(column = "id",property = "id",javaType = Integer.class,jdbcType= JdbcType.INTEGER),
            @Result(column = "role_name",property = "roleName",javaType = String.class,jdbcType= JdbcType.VARCHAR)
    })
    List<SysRole> getUserRolesByUserName(@Param("name") String userName);

    /**
     * 通过角色查询权限信息
     *
     * @param roles
     * @return List<SysPermission>
     * @author Ma_wei
     * @since 2019/7/12
     */
    @SelectProvider(type=SysUserDaoProvider.class,method="getUserPermissionsByRoleChart")
    @Results(value = {
            @Result(column = "id",property = "id",javaType = Integer.class,jdbcType= JdbcType.INTEGER),
            @Result(column = "name",property = "name",javaType = String.class,jdbcType= JdbcType.VARCHAR)
    })
    List<SysPermission> getUserPermissionsByRole (@Param("roles") List<SysRole> roles );

    /**
     * 通过用户名称获取用户信息
     *
     * @param userName
     * @return SysUser
     * @author Ma_wei
     * @since 2019/7/12
     */
    @Select("select x.id,x.`name`,x.`password`,x.salt from sys_user x where x.`name` = '#{name}'")
    @Results(value = {
            @Result(column = "id",property = "id",javaType = Integer.class,jdbcType= JdbcType.INTEGER),
            @Result(column = "name",property = "name",javaType = String.class,jdbcType= JdbcType.VARCHAR),
            @Result(column = "password",property = "password",javaType = String.class,jdbcType= JdbcType.VARCHAR),
            @Result(column = "salt",property = "salt",javaType = String.class,jdbcType= JdbcType.VARCHAR)
    })
    SysUser getUserInfoByName (@Param("name") String userName);

    /**
     * 内部类解析sql语句
     *
     * @param
     * @return 
     * @author Ma_wei
     * @since 2019/7/12
     */
    class SysUserDaoProvider {

        /**
         * 拼接通过角色查询权限信息sql语句
         *
         * @param paramMap
         * @return String
         * @author Ma_wei
         * @since 2019/7/12
         */
        public String getUserPermissionsByRoleChart(Map<String,List<SysRole>> paramMap) {
            log.debug("进入ISysUserDao.$SysUserDaoProvider.getUserPermissionsByRoleChart方法()...");
            log.info("获取到传入参数为：{}",paramMap);
            List<SysRole> _roles = paramMap.get("roles");
            StringBuilder sql = new StringBuilder();
            //select x.id,x.name from sys_permission x RIGHT JOIN (select z.permission_id from sys_role_permission z where z.role_id IN (2,3)) y on  y.permission_id = x.id
            sql.append("select x.id,x.name from ")
                    .append(SYS_PERMISSION_TABLENAME)
                    .append(" x RIGHT JOIN (select z.permission_id from ")
                    .append(SYS_ROLE_PERMISSION_TABLENAME).append("z where 1 = 1 ");
            ListIterator<SysRole> iterator = _roles.listIterator();
            String inStr = "and z.role_id IN (";
            while (iterator.hasNext()) {
                SysRole _role = iterator.next();
                int _id = _role.getId();
                inStr = inStr + _id;
                if (!iterator.hasNext()) {
                    inStr = inStr + ")";
                } else {
                    inStr = inStr + ",";
                }
            }
            if (!_roles.isEmpty()) {
                sql.append(inStr);
            }
            sql.append(") y on  y.permission_id = x.id");
            log.info("拼接通过角色查询权限信息sql语句为：{}",sql.toString());
            return sql.toString();
        }
    }
}
