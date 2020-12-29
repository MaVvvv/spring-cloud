package com.mxw.hxb.springboot.dao.sopdb;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.mxw.hxb.springboot.po.sopdb.SopServiceAuthClientPO;
import com.mxw.hxb.springboot.po.sopdb.SopServiceAuthPO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * 开放平台服务授权表映射类
 *
 * @author Ma_wei
 * @since 2020/11/25 13:53
 */
@DS(value = "sop_db")
@Mapper
public interface SopServiceAuthMapper {

    String TABLE_NAME = "sop_service_auth";
    String TABLE_SERVICE_NAME = "sop_service";

    @Select("select t1.id,t1.purchaser_id,t1.service_id,t2.category,t1.client_id,t1.client_secret,t1.user_name,t1.`password` FROM " +
            "(select * from " + TABLE_NAME +" where client_id is not null and client_secret is not null) t1 " +
            "left JOIN " + TABLE_SERVICE_NAME +" t2 " +
            "on " +
            "t1.service_id = t2.id " +
            "HAVING t2.category is not null " +
            "ORDER BY t1.id;")
    @Results(value = {
            @Result(column = "id",property = "id",javaType = Long.class,jdbcType= JdbcType.INTEGER),
            @Result(column = "purchaser_id",property = "purchaserId",javaType = Long.class,jdbcType= JdbcType.INTEGER),
            @Result(column = "service_id",property = "serviceId",javaType = Long.class,jdbcType= JdbcType.INTEGER),
            @Result(column = "category",property = "category",javaType = String.class,jdbcType= JdbcType.VARCHAR),
            @Result(column = "client_id",property = "clientId",javaType = String.class,jdbcType= JdbcType.VARCHAR),
            @Result(column = "client_secret",property = "clientSecret",javaType = String.class,jdbcType= JdbcType.VARCHAR),
            @Result(column = "user_name",property = "userName",javaType = String.class,jdbcType= JdbcType.VARCHAR),
            @Result(column = "password",property = "password",javaType = String.class,jdbcType= JdbcType.VARCHAR)
    })
    List<SopServiceAuthPO> getSopServiceAuthLists();

    @Update("update " + TABLE_NAME + " set client_infos = #{po.clientInfos} where id = #{po.id}")
    void updateSopServiceAuthClientInfo(@Param(value = "po") SopServiceAuthClientPO po);
}
