package com.coding.mybatisplus.config;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Desciption 数据库连接池自定义属性
 * @Author Shu WJ
 * @DateTime 2019-07-20 00:01
 * @Version 1.0
 **/
@Configuration
public class DruidDataSourceAutoConfig {

  /**
   * @ConfigurationProperties(prefix = "spring.datasource")：作用就是将 全局配置文件中 前缀为 spring.datasource
   * 的属性值注入到 com.alibaba.druid.pool.DruidDataSource 的同名参数中
   */
  @ConfigurationProperties(prefix = "spring.datasource.druid")
  @Bean
  @ConditionalOnMissingBean
  public DataSource dataSource() {
    return new DruidDataSource();
  }
}

