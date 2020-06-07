package cool.mawei.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cool.mawei.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-06-02 19:48
 */
@Mapper
@Repository
public interface UserDao extends BaseMapper<User> {
    // 所有的CRUD操作都已经编写完成了 不需要在想以前配置一大堆文件了
}
