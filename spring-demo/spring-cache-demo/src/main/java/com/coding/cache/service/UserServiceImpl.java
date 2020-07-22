package com.coding.cache.service;

import java.util.Optional;

import com.coding.cache.entity.User;
import com.coding.cache.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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

  //  调用这个后，如果执行目标方法后，仍然返回 null，是否会插入缓存中? 答案是会缓存。
  // if no value is found in the cache for the computed key, the target method will be invoked and the returned value stored in the associated cache.
  //  Note that Java8's Optional return types are automatically handled and its content is stored in the cache if present.
  @Cacheable(value = { "user"}, key = "#id")
  public Optional<User> getUser(String id) {
    log.info("缓存未命中，执行查询语句");
    return userRepository.findById(id);
  }

  // 这个注释可以确保方法被执行，同时方法的返回值也被记录到缓存中
  // it always causes the method to be invoked and its result to be stored in the associated cache
  @CachePut(
      value = {"user"},
      key = "#user.id")
  public User insertUser(User user) {
    log.info("新增数据：{}", user);
    userRepository.save(user);
    return user;
  }
}
