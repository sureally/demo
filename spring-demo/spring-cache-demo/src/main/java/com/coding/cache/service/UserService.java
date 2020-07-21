package com.coding.cache.service;

import com.coding.cache.entity.User;

/**
 * @Author shu wj
 * @Date 2020/7/20 23:06
 * @Description
 */
public interface UserService {

  User getUser(String  id);

  User insertUser(User user);
}
