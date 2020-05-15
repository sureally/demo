package com.coding.transaction.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

/**
 * @Author shu wj
 * @Date 2020/5/12 22:32
 * @Description
 */
@Data
@Entity
public class Employee {

  @Id
//  @GeneratedValue(generator = "system-uuid")
//  @GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
//  @Column(length = 32)
  private String empId;

  private String empName;
}
