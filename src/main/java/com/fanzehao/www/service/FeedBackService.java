package com.fanzehao.www.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fanzehao.www.entity.FeedBack;
import com.fanzehao.www.entity.User;
import com.fanzehao.www.mapper.FeedBackMapper;
import org.springframework.stereotype.Service;

@Service
public class FeedBackService extends ServiceImpl<FeedBackMapper, FeedBack> {

}
