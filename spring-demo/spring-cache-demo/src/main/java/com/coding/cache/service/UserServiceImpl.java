package com.coding.cache.service;

import com.coding.cache.entity.User;
import com.coding.cache.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author shu wj
 * @Date 2020/7/20 22:59
 * @Description
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Transactional
  @Cacheable(value = { "user" }, key = "#id")
  public User getUser(String id) {
    log.info("缓存未命中，执行查询语句");
    return userRepository.findById(id).orElse(null);
  }

  // TODO: CachePut 必须返回值，才能存入缓存？
  // 这个注释可以确保方法被执行，同时方法的返回值也被记录到缓存中
  @CachePut(value = { "user" }, key = "#user.id")
  public User insertUser(User user) {
    log.info("新增数据：{}", user);
    userRepository.save(user);
    return user;
  }
}
