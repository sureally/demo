package com.coding.cache.config;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Scheduler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@EnableCaching
@Configuration
public class CacheConfig extends CachingConfigurerSupport {

  @Bean
  public CacheManager cacheManager() {
    CaffeineCacheManager cacheManager = new CaffeineCacheManager();
    // 设置缓存无效化：spring.cache.type=none
    // 缓存对象必须实现Serializable
    // 除GuavaCacheManager之外都支持Spring事务，即回滚时Cache的数据也会被移除
    ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(2);
    // 方案一(常用)：定制化缓存Cache
    cacheManager.setCaffeine(
        Caffeine.newBuilder()
            .expireAfterWrite(1, TimeUnit.SECONDS)
            .initialCapacity(10)
            .maximumSize(10_0)
            .removalListener(
                (key, value, cause) -> {
                  log.info("Key {} was removed {} value is {}", key, cause, value);
                })
            .scheduler(
                Scheduler.guardedScheduler(
                    Scheduler.forScheduledExecutorService(newScheduledThreadPool))));
    // 如果缓存种没有对应的value，通过createExpensiveGraph方法同步加载  buildAsync是异步加载
    // .build(key -> createExpensiveGraph(key))

    cacheManager.setAllowNullValues(true);

    // 方案二：传入一个CaffeineSpec定制缓存，它的好处是可以把配置方便写在配置文件里
    // cacheManager.setCaffeineSpec(CaffeineSpec.parse("initialCapacity=50,maximumSize=500,expireAfterWrite=5s"));
    return cacheManager;
  }
}
