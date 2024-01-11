package com.fanzehao.www.controller;

import com.fanzehao.www.entity.FeedBack;
import com.fanzehao.www.entity.User;
import com.fanzehao.www.service.FeedBackService;
import com.fanzehao.www.service.UserService;
import com.fanzehao.www.util.Assert;
import com.fanzehao.www.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class FeedBackController {
    @Autowired
    protected FeedBackService feedBackService;

    @RequestMapping("/feedback.html")
    public String sumbit()
    {
        System.out.println("执行feedback页面");
        return "feedback";
    }
    @PostMapping("/feedback/save")
    //表示服务端返回JSON数据【异步一般都是以JSON数据格式进行交互】
    @ResponseBody
    public RespResult feedBack(FeedBack feedBack)
    {
        feedBackService.save(feedBack);
        return RespResult.success("提交成功");
    }
}
