package com.coding.async.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author shu wj
 * @Date 2019/11/8 23:57
 * @Description
 */
@Data
@Component
@ConfigurationProperties(prefix = "async.task.pool")
public class NativeTaskThreadPoolProperties {
  private int corePoolSize;

  private int maxPoolSize;

  private int keepAliveSeconds;

  private int queueCapacity;

  private String threadNamePrefix;
}
