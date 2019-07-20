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
      // 很多时候你不用调用 rollback()，因为 MyBatis 会在你没有调用 commit 时替你完成回滚操作。(官方介绍)
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
    user.setName("0");
    user.setPassword("0");
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      UserDao userDao = sqlSession.getMapper(UserDao.class);
      userDao.insert(user);
      user.setName("1");
      userDao.insert(user);
      user.setName("failed");
      userDao.insert(user);
      sqlSession.commit();
    }
  }
}
