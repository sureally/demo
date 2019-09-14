package com.coding.mybatisplus.config;

import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName com.coding.mybatisplus.config.MybatisPlusConfig
 * @Desciption
 * @Author Shu WJ
 * @DateTime 2019-09-14 17:06
 * @Version 1.0
 **/
@Configuration
@MapperScan(basePackages = {"com.coding.mybatisplus.dao"})
@EnableTransactionManagement
public class MybatisPlusConfig {
  /**
   * 性能分析拦截器，不建议生产使用
   */
  @Bean
  public PerformanceInterceptor performanceInterceptor(){
    PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();

    performanceInterceptor.setWriteInLog(true);
    // 执行的最大时长，如果超时便抛出异常。如果writeInLog设置为true，便将异常写入日志，而不停止运行
    performanceInterceptor.setMaxTime(100);
    return performanceInterceptor;
  }

}
