package com.coding.mybatisplus;

import javax.annotation.Resource;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName PACKAGE_NAME.com.coding.mybatisplus.DataSourceApplicationTest
 * @Desciption
 * @Author Shu WJ
 * @DateTime 2019-09-14 16:30
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataSourceApplicationTest {
  @Resource
  DataSource dataSource;

  @Test
  public void contextLoads() throws SQLException {

    System.out.println("数据源>>>>>>" + dataSource.getClass());
    Connection connection = dataSource.getConnection();

    System.out.println("连接>>>>>>>>>" + connection);
    System.out.println("连接地址>>>>>" + connection.getMetaData().getURL());

    DruidDataSource druidDataSource = (DruidDataSource) dataSource;
    System.out.println("druidDataSource 数据源最大连接数：" + druidDataSource.getMaxActive());
    System.out.println("druidDataSource 数据源初始化连接数：" + druidDataSource.getInitialSize());

    connection.close();
  }

}
