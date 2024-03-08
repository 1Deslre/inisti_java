package com.deslre.demospringbootstarter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.deslre.demospringbootstarter.entity.User;
import com.deslre.demospringbootstarter.mapper.UserMapper;
import com.deslre.demospringbootstarter.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2024-03-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
