package com.demo;

import com.demo.dao.UserDao;
import com.demo.model.User;
import org.apache.ibatis.session.SqlSession;

import static com.demo.util.MyBatisUtil.sqlSessionFactory;

/**
 * @ClassName com.demo.Application
 * @Desciption 入口
 * @Author Shu WJ
 * @DateTime 2019-07-20 00:07
 * @Version 1.0
 **/
public class Application {

  private static void testInsert(User user) {
    // 默认是 手动提交
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      UserDao userDao = sqlSession.getMapper(UserDao.class);
      userDao.insert(user);
      // 需要commit才能提交。
      sqlSession.commit();
    }
  }

  private static void testUpdate(User user) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      UserDao userDao = sqlSession.getMapper(UserDao.class);
      userDao.updateSelection(user);
      sqlSession.commit();
    }
  }

  private static User testSelect(Long id) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      UserDao userDao = sqlSession.getMapper(UserDao.class);
      User user =userDao.selectById(id);
      sqlSession.commit();
      return user;
    }
  }

  public static void main(String[] args) {
    User user = new User();
    user.setName("test");
    user.setPassword("000");
    testInsert(user);

    user.setName(null);
    user.setPassword("update");
    testUpdate(user);

    System.out.println(testSelect(user.getId()));
  }
}
