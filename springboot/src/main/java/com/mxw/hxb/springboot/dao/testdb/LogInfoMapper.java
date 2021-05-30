package com.mxw.hxb.springboot.dao.testdb;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.*;

import java.util.Date;

/**
 * 日志数据库映射
 *
 * @author Ma_wei
 * @since 2021/2/7 16:58
 */
@DS(value = "test_db")
@Mapper
public interface LogInfoMapper {

    String TABLE_NAME = "log_info";

    @Insert("insert into " + TABLE_NAME + "(`param`,`create_time`) values (#{param},#{createTime})")
    void insert(@Param("param")String param ,@Param("createTime") Date createTime);
}
