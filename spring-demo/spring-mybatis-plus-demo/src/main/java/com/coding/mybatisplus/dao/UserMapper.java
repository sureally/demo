package com.coding.mybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coding.mybatisplus.model.User;
import org.springframework.stereotype.Component;

/**
 * @InterfaceName com.coding.mybatisplus.dao.UserMapper
 * @Desciption
 * @Author Shu WJ
 * @DateTime 2019-09-14 16:50
 * @Version 1.0
 **/
@Component
public interface UserMapper extends BaseMapper<User> {
}
