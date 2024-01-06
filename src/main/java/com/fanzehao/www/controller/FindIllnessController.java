package com.fanzehao.www.controller;

import com.fanzehao.www.vo.request.FeedBackRequestVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FindIllnessController {
    @RequestMapping("/findIllness")
    public String FindIllness(FeedBackRequestVo vo)
    {

        System.out.println(vo);
        System.out.println("执行find页面");

        return "illness";
    }

}
