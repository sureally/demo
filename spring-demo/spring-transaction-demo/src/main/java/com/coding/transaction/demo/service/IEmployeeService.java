package com.coding.transaction.demo.service;

import com.coding.transaction.demo.entity.Employee;

/**
 * @Author shu wj
 * @Date 2020/5/15 20:30
 * @Description
 */
public interface IEmployeeService {
  void insertEmployee(Employee emp);

  void deleteEmployById(String empid);
}
