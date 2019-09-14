package com.coding.mybatisplus.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coding.mybatisplus.SpringDemoMybatisPlusApplicationTest;
import com.coding.mybatisplus.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 */
@Slf4j
public class UserServiceTest extends SpringDemoMybatisPlusApplicationTest {
    @Autowired
    private IUserService userService;

    @Test
    public void testQueryOne() {
        User user = userService.getById(22L);
        log.debug(" user = {}", user);
    }


    @Test
    public void testInsertOne() {
        User user = User.builder().name("shu").password("123456").build();
        log.debug(" new user = {}", user);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
    }
}