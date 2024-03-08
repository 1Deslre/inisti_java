package com.alive.neo4jdemo.service.impl;

import com.alive.neo4jdemo.entity.User;
import com.alive.neo4jdemo.mapper.UserMapper;
import com.alive.neo4jdemo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
