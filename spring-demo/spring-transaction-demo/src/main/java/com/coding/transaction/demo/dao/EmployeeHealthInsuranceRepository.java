package com.coding.transaction.demo.dao;

import com.coding.transaction.demo.entity.EmployeeHealthInsurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author shu wj
 * @Date 2020/5/15 20:28
 * @Description
 */
@Repository
public interface EmployeeHealthInsuranceRepository extends JpaRepository<EmployeeHealthInsurance, Integer> {

  void deleteByEmpId(String empId);
}
