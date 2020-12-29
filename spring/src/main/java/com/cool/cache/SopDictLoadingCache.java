package com.cool.cache;

import com.cool.service.ISpringIocService;
import com.google.common.base.Joiner;
import com.google.common.cache.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 本地loadingCache抽象类
 *
 * @author Ma_wei
 * @since  2020/7/14
 */
@Slf4j
@Component
public class SopDictLoadingCache {

    /** 最大缓存数*/
    private static final long CACHE_MAX_SIZX = 100000L;

    /** 缓存天数*/
    private static final long CACHE_DAY = 1;

    private static LoadingCache<String,String> LOADING_CACHE = null;

    public SopDictLoadingCache(ISpringIocService springIocServiceAnImpl) {
        try {
            // 处理缓存键不存在缓存值时的处理逻辑
            LOADING_CACHE = loadCache(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    // 处理缓存键不存在缓存值时的处理逻辑
                    System.out.println("run load...");
                    return springIocServiceAnImpl.showIoc();
                }
            });
        } catch (Exception e) {
            log.error("初始化Guava Cache出错", e);
        }
    }

    /**
     * 全局缓存设置
     *
     * 缓存项最大数量：100000
     * 缓存有效时间（天）：10
     *
     * @param cacheLoader
     * @return LoadingCache<String, String>
     */
    private static LoadingCache<String, String> loadCache(CacheLoader<String, String> cacheLoader) throws Exception {
        return CacheBuilder.newBuilder()
                //缓存池大小，在缓存项接近该大小时， Guava开始回收旧的缓存项
                .maximumSize(CACHE_MAX_SIZX)
                //设置时间对象没有被读/写访问则对象从内存中删除(在另外的线程里面不定期维护)
                .expireAfterAccess(CACHE_DAY, TimeUnit.SECONDS)
                // 设置缓存在写入之后 设定时间 后失效
                .expireAfterWrite(CACHE_DAY, TimeUnit.SECONDS)
                //开启Guava Cache的统计功能
                .recordStats()
                .build(cacheLoader);
    }

    /**
     * 通过字典类型与code获取缓存值
     * 
     * @param type
     * @return String
     * @author Ma_wei
     * @since  2020/7/14
     */
    public String get(String type,String code){
        String key = Joiner.on(":").join(type,code);
        try {
            return LOADING_CACHE.get(key);
        } catch (ExecutionException e) {
            log.error("SopDictLoadingCache.get({}) error {}!",key,e.getMessage());
        }
        return null;
    }

    /**
     * 刷新对应key值西信息
     * 
     * @param type
     * @param code
     * @author Ma_wei
     * @since  2020/7/14
     */
    public void refresh(String type,String code){
        LOADING_CACHE.refresh(Joiner.on(":").join(type,code));
    }
}
