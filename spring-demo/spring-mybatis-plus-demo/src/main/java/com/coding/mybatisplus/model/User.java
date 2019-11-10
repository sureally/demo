package com.coding.mybatisplus.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName com.demo.model.User
 * @Desciption 实体类
 * @Author Shu WJ
 * @DateTime 2019-07-20 00:08
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("user")
public class User implements Serializable {
  private static final long serialVersionUID = -1840831686851699943L;

  private Long id;
  private String name;
  private String password;
  private Date createdTime;
  private Date updatedTime;
}
