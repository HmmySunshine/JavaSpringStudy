package com.fanzehao.www.controller;


import com.fanzehao.www.config.FZHConfig;
import com.fanzehao.www.entity.User;
import com.fanzehao.www.service.UserService;
import com.fanzehao.www.util.Assert;
import com.fanzehao.www.util.RespResult;
import com.fanzehao.www.vo.request.FeedBackRequestVo;
import com.fanzehao.www.vo.response.FeedBackResponseVo;
import com.fanzehao.www.vo.response.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    private FZHConfig fzhConfig;
    @Autowired
    private UserService userService;
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
    @RequestMapping("/doctor")
    public String Doctor()
    {
        return "doctor";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session)
    {
        session.invalidate();
        return "redirect:/index";
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
    //更新资料
    @PostMapping("/user/saveProfile")
    @ResponseBody
    public RespResult updateProfile(User user,HttpSession session)
    {
        System.out.println(user);
        User loginUser = (User) session.getAttribute("loginUser");
        loginUser.setUserName(user.getUserName());
        loginUser.setUserTel(user.getUserTel());
        loginUser.setUserAge(user.getUserAge());




        Date d = new Date();
        System.out.println(d);
        user.setUpdateTime(d);
        userService.saveOrUpdate(user);
        System.out.println("After update: " + user);

        return RespResult.success("更新成功");
    }

    @PostMapping("user/savePassword")
    @ResponseBody
    public RespResult updatePassword(HttpSession session, @RequestParam String oldPass,
                                     @RequestParam String newPass)
    {
        User loginUser = (User) session.getAttribute("loginUser");
        System.out.println(oldPass);
        User userByUserPwd = userService.findUserByUserPwd(loginUser);
        System.out.println(userByUserPwd.getUserPwd());
        if (oldPass.equals(userByUserPwd.getUserPwd()))
        {
            userByUserPwd.setUserPwd(newPass);
            userService.saveOrUpdate(userByUserPwd);
            System.out.println("After update: " + userByUserPwd);
            return RespResult.success("更新密码成功");
        }
        System.out.println(newPass);
        return RespResult.fail("更新密码失败");



    }
}
