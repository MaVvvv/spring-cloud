package com.channelsoft.druid.domain;

import com.channelsoft.druid.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-06-06 10:10
 */
@Mapper
@Repository
public interface UserDao {

    @Select("select * from user")
    @Results(value = {@Result(column = "id",property = "id",javaType = String.class,jdbcType= JdbcType.VARCHAR)})
    List<UserDTO> getUser ();
}
