package cool.mawei.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-06-04 14:33
 */
@SpringBootConfiguration
public class MybatisPlusConfig {

    /**
     * mybatis的乐观锁实现
     *
     * @author Ma_wei
     * @since 2020/6/4
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }

    /**
     * mybatis内置分页插件
     *
     * @author Ma_wei
     * @since 2020/6/4
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
