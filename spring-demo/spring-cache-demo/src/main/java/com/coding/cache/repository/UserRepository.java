package com.coding.cache.repository;

import com.coding.cache.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author shu wj
 * @Date 2020/7/20 22:59
 * @Description
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
