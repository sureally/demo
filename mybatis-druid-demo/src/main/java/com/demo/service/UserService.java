package com.demo.service;

import java.sql.SQLIntegrityConstraintViolationException;

import com.demo.dao.UserDao;
import com.demo.model.User;
import org.apache.ibatis.session.SqlSession;

import static com.demo.util.MyBatisUtil.sqlSessionFactory;

/**
 * @ClassName com.demo.service.UserService
 * @Desciption
 * @Author Shu WJ
 * @DateTime 2019-07-20 17:42
 * @Version 1.0
 **/
public class UserService {

  /** 处理 mybatis 异常*/
  public void insertSelection(User user) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      UserDao userDao = sqlSession.getMapper(UserDao.class);
      try {
        userDao.insert(user);
      } catch (Exception e) {
        if (null == e.getCause()) {
          // 没有被包装的异常，继续抛出
          throw e;
        }

        Throwable nested = e.getCause();
        if (nested instanceof SQLIntegrityConstraintViolationException) {
          // 因违背主键约束/唯一索引/外键所产生的异常
          // 在这里，想实现的逻辑是，如果insert user，发现已经存在该id的记录时，更新该记录
          userDao.updateSelection(user);
        }
      }
      // 需要commit才能提交。
      // 很多时候你不用调用 rollback()，因为 MyBatis 会在你没有调用 commit 时替你完成回滚操作。(官方介绍)
      sqlSession.commit();
    }
  }
}
