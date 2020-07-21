package com.coding.cache.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author shu wj
 * @Date 2020/7/20 22:59
 * @Description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements Serializable {

  @Id
  @Column( nullable = false)
  private String id;

  private String name;

  private String password;
}
