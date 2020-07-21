package com.coding.cache.service;

import com.coding.cache.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

  @Autowired private UserService userService;

  @Test
  public void getUser() {
    insertUser();

    System.out.println(userService.getUser("1"));

    System.out.println(userService.getUser("2"));
  }

  @Test
  public void insertUser() {
    userService.insertUser(User.builder().id("1").name("abc").password("123").build());
  }
}
