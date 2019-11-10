package com.coding.async.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @Author shu wj
 * @Date 2019/11/7 23:40
 * @Description springboot异步线程的使用线程池
 */
@Slf4j
@Configuration
@AllArgsConstructor
@EnableAsync
public class NativeAsyncTaskExecutePool implements AsyncConfigurer {

  private final NativeTaskThreadPoolProperties nativeTaskThreadPoolProperties;

  @Bean("nativeAsyncTaskExecutor") // bean的名称，默认为首字母小写的方法名
  @Override
  public Executor getAsyncExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(nativeTaskThreadPoolProperties.getCorePoolSize());
    executor.setMaxPoolSize(nativeTaskThreadPoolProperties.getMaxPoolSize());
    executor.setKeepAliveSeconds(nativeTaskThreadPoolProperties.getKeepAliveSeconds());
    executor.setQueueCapacity(nativeTaskThreadPoolProperties.getQueueCapacity());
    executor.setThreadNamePrefix(nativeTaskThreadPoolProperties.getThreadNamePrefix());
    // 拒绝策略
    // 此处为由调用者所在的线程来处理
    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    return executor;
  }

  /**
   * 异步任务中的异常处理。默认的一般应该够用
   * @return
   */
  @Override
  public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
    return null;
  }
}
