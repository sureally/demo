package com.coding.cache.service;

import com.coding.cache.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

  @Autowired private UserService userService;

  @Test
  public void getUser() throws InterruptedException {
    insertUser();

    log.info("{}", userService.getUser("1"));
    log.info("{}", userService.getUser("1"));

    log.info("{}", userService.getUser("2"));
    log.info("{}", userService.getUser("2"));

    Thread.sleep(5000);

    log.info("{}", userService.getUser("3"));

    Thread.sleep(5000);

    log.info("{}", userService.getUser("1"));

    log.info("{}", userService.getUser("1"));
  }

  @Test
  public void insertUser() {
    userService.insertUser(User.builder().id("1").name("abc").password("123").build());
  }
}
