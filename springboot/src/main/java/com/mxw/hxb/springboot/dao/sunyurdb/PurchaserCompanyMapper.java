package com.mxw.hxb.springboot.dao.sunyurdb;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.mxw.hxb.springboot.po.sunyurdb.PurchaserCompanyPO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * 租户公司主体信息数据库映射实例
 *
 * @author Ma_wei
 * @since 2020/11/25 14:42
 */
@DS(value = "sunyur_db")
@Mapper
public interface PurchaserCompanyMapper {

    String TABLE_NAME = "purchaser_company";

    @Select("select DISTINCT code, purchaser_id" +
            " from " + TABLE_NAME +
            " where purchaser_id = #{purchaserId} and is_deleted = 0 and state = 0")
    @Results(value = {
            @Result(column = "purchaser_id",property = "purchaserId",javaType = Long.class,jdbcType= JdbcType.INTEGER),
            @Result(column = "code",property = "code",javaType = String.class,jdbcType= JdbcType.VARCHAR)
    })
    List<PurchaserCompanyPO> getPurchaserCompanyLists(@Param(value = "purchaserId") Long purchaserId);
}
