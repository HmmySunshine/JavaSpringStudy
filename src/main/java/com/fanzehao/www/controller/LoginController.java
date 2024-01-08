package com.fanzehao.www.controller;

import cn.hutool.core.util.StrUtil;
import com.fanzehao.www.util.EmailClient;
import com.fanzehao.www.util.RespResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;
@Controller
//在类中使用，表示访问该控制器，必须以/login开头
@RequestMapping("/login")
public class LoginController {
    //注入发送邮件的工具类
    @Autowired
    protected EmailClient emailClient;
    //声明会话对象
    //发送邮件后，验证码保存在会话中
    //当执行注册时，从会话中取出验证码数据跟用户输入的验证码做比对
    protected HttpSession session;
    //请求对象
    protected HttpServletRequest request;
    //相应的对象
    protected HttpServletResponse response;
    //必须是/login/sendEmailCode的URL,并且以POST方式提交的请求会到达该方法
    @PostMapping("/sendEmailCode")
    //表示服务端返回JSON数据【异步一般都是以JSON数据格式进行交互】
    @ResponseBody
    //JSON数据的返回，返回类型使用RespResult
    //String email  前端请求的参数名
    //Map<String, Object> map  会自动创建一个Map对象到方法中
    public RespResult sendEmailCode(String email, Map<String, Object> map) {
        System.out.println("22222");
        //先判断邮箱地址是否为空
        if (StrUtil.isEmpty(email)) {
            //只有静态方法可以使用类名直接访问
            //静态方法是以static修饰的方法
            //静态方法内部不能使用this关键字
            return RespResult.fail("邮箱不可为空");
        }
        // 发送验证码
        String verifyCode = emailClient.sendEmailCode(email);
        //把邮箱地址，验证码，有效期写入会话对象中
        map.put("email", email);
        map.put("code", verifyCode);
        map.put("time", new Date());
        //写入会话对象中
        session.setAttribute("EMAIL_CODE" + email, map);
        return RespResult.success("发送成功");
    }
    /**
     * 在每个子类方法调用之前先调用
     */
    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("1111");
        //初始化请求对象 响应对象 会话对象
        this.request = request;
        this.response = response;
        this.session = request.getSession(true);
        //loginUser = (User) session.getAttribute("loginUser");
        //session.setAttribute("kindList", illnessKindService.findList());
    }
}