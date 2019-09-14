package com.coding.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coding.mybatisplus.dao.UserMapper;
import com.coding.mybatisplus.model.User;
import com.coding.mybatisplus.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @ClassName com.coding.mybatisplus.service.impl.UserServiceImpl
 * @Desciption
 * @Author Shu WJ
 * @DateTime 2019-09-14 16:51
 * @Version 1.0
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
