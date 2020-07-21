package com.coding.cache.config;

import java.util.concurrent.TimeUnit;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class CacheConfig extends CachingConfigurerSupport {

  @Bean
  public CacheManager cacheManager() {
    CaffeineCacheManager cacheManager = new CaffeineCacheManager();
    // 方案一(常用)：定制化缓存Cache
    cacheManager.setCaffeine(
        Caffeine.newBuilder()
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .initialCapacity(10)
            .maximumSize(10_0))
    // 如果缓存种没有对应的value，通过createExpensiveGraph方法同步加载  buildAsync是异步加载
    // .build(key -> createExpensiveGraph(key))
    ;
    cacheManager.setAllowNullValues(false);

    // 方案二：传入一个CaffeineSpec定制缓存，它的好处是可以把配置方便写在配置文件里
    // cacheManager.setCaffeineSpec(CaffeineSpec.parse("initialCapacity=50,maximumSize=500,expireAfterWrite=5s"));
    return cacheManager;
  }
}
