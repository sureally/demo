package com.coding.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

/**
 * @Author shu wj
 * @Date 2019/11/7 22:30
 * @Description
 */
@Slf4j
@SpringBootApplication
public class SpringAsyncApplication  {
  public static void main(String[] args) {
    SpringApplication.run(SpringAsyncApplication.class, args);
  }
}
