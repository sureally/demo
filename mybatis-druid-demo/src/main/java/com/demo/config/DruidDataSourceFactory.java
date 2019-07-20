package com.demo.config;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.datasource.DataSourceFactory;


/**
 * @ClassName com.demo.config.DruidDataSourceFactory
 * @Desciption 数据库连接池自定义属性
 * @Author Shu WJ
 * @DateTime 2019-07-20 00:01
 * @Version 1.0
 **/
public class DruidDataSourceFactory implements DataSourceFactory {

  private Properties properties;

  @Override
  public void setProperties(Properties properties) {
    this.properties = properties;
  }

  @Override
  public DataSource getDataSource() {
    DruidDataSource dataSource = new DruidDataSource();
    dataSource.setDriverClassName(this.properties.getProperty("driver"));
    dataSource.setUrl(this.properties.getProperty("url"));
    dataSource.setUsername(this.properties.getProperty("username"));
    dataSource.setPassword(this.properties.getProperty("password"));
    dataSource.setInitialSize(Integer.valueOf(this.properties.getProperty("initialSize")));
    dataSource.setMinIdle(Integer.valueOf(this.properties.getProperty("minIdle")));
    dataSource.setMaxActive(Integer.valueOf(this.properties.getProperty("maxActive")));
    dataSource.setMaxWait(Integer.valueOf(this.properties.getProperty("maxWait")));
    dataSource.setTestOnBorrow(Boolean.valueOf(this.properties.getProperty("testOnBorrow")));
    dataSource.setTestOnReturn(Boolean.valueOf(this.properties.getProperty("testOnReturn")));
    dataSource.setTestWhileIdle(Boolean.valueOf(this.properties.getProperty("testWhileIdle")));

    dataSource.setTimeBetweenEvictionRunsMillis(Long.valueOf(this.properties.getProperty("timeBetweenEvictionRunsMillis")));
    dataSource.setValidationQuery(this.properties.getProperty("validationQuery"));
    dataSource.setKeepAlive(Boolean.valueOf(this.properties.getProperty("keepAlive")));
    try {
      dataSource.init();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return dataSource;
  }
}

