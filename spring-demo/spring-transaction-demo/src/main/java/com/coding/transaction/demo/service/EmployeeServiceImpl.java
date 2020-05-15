package com.coding.transaction.demo.service;

import com.coding.transaction.demo.dao.EmployeeRepository;
import com.coding.transaction.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author shu wj @Date 2020/5/12 22:50 @Description The service will perform Employee Operations
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {

  @Autowired private EmployeeRepository employeeRepository;

  @Override
  public void insertEmployee(Employee emp) {
    employeeRepository.save(emp);
  }

  @Override
  public void deleteEmployById(String empid) {
    employeeRepository.deleteById(empid);
  }
}
