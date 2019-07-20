package com.demo.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * @ClassName com.demo.util.MyBatisUtil
 * @Desciption
 * @Author Shu WJ
 * @DateTime 2019-07-20 00:26
 * @Version 1.0
 **/
public class MyBatisUtil {
  private static final String CONFIG_FILE = "mybatis-config.xml";

  public static SqlSessionFactory sqlSessionFactory;

  static {
    try (InputStream inputStream = Resources.getResourceAsStream(CONFIG_FILE)) {
      sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // 推荐用法
  // try (SqlSession sqlSession = sqlSessionFactory.openSession()) {}

}

