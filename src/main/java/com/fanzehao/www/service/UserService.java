package com.fanzehao.www.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fanzehao.www.entity.User;
import com.fanzehao.www.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {
}
