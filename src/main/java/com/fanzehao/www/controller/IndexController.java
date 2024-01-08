package com.fanzehao.www.controller;


import com.fanzehao.www.config.FZHConfig;
import com.fanzehao.www.entity.User;
import com.fanzehao.www.util.Assert;
import com.fanzehao.www.vo.request.FeedBackRequestVo;
import com.fanzehao.www.vo.response.FeedBackResponseVo;
import com.fanzehao.www.vo.response.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    private FZHConfig fzhConfig;

    @RequestMapping("/index")
    public String Index(Model model, FeedBackRequestVo vo)
    {
        System.out.println("fzh范泽昊的配置信息" + fzhConfig);
        System.out.println(vo);
        model.addAttribute("username","23311117范泽昊软件");
        FeedBackResponseVo testVo = new FeedBackResponseVo();
        testVo.setName("泽昊企业");
        testVo.setEmail("zehao@gamil.com");
        testVo.setTitle("厉害厉害");
        testVo.setContent("China No.1");
        Map<Integer, String> map = new HashMap<>();
        map.put(4, "faker");

        model.addAttribute("myMap", map);


        Student d = new Student("23311117", "范泽昊");
        model.addAttribute("config", fzhConfig);
        model.addAttribute("Student",d);
        model.addAttribute("testVO",testVo);

        return "index";
    }

    @GetMapping("/logout")
    public String logout()
    {
        System.err.println();
        return "logout";
    }
//    @GetMapping("/profile")
//    public String Profile()
//    {
//        return "profile";
//    }

    @GetMapping("/profile")
    public String profile(HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (Assert.isEmpty(loginUser)) {
            return "redirect:/index";
        }
        return "profile";
    }
}
