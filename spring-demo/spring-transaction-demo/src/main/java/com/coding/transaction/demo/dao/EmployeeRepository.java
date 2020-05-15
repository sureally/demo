package com.coding.transaction.demo.dao;

import com.coding.transaction.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author shu wj
 * @Date 2020/5/12 22:32
 * @Description
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

}
