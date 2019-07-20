package com.demo.model;

import java.util.Date;

import lombok.Data;

/**
 * @ClassName com.demo.model.User
 * @Desciption 实体类
 * @Author Shu WJ
 * @DateTime 2019-07-20 00:08
 * @Version 1.0
 **/
@Data
public class User {
  private Long id;
  private String name;
  private String password;
  private Date createdTime;
  private Date updatedTime;
}
