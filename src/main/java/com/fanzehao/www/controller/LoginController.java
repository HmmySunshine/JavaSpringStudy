package com.fanzehao.www.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fanzehao.www.entity.User;
import com.fanzehao.www.service.UserService;
import com.fanzehao.www.util.Assert;
import com.fanzehao.www.util.EmailClient;
import com.fanzehao.www.util.RespResult;
import com.fanzehao.www.util.ValidationUtils;
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
    protected UserService userService;
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
    private static final float CURRENT_TIME = 5 * 60 * 1000;
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
    @PostMapping("/register")
    @ResponseBody
    public RespResult register(User user, String code)
    {
        System.out.println(user);

        System.out.println(code);
        String email = user.getUserEmail();

        String tel = user.getUserTel();
        if (Assert.isEmpty(email))
        {
            return RespResult.fail("邮箱为空");
        }
        if (Assert.isEmpty(tel))
        {
            return RespResult.fail("手机号为空");
        }
        //其他参数非空自己补充
        //验证输入的手机号手机和邮箱格式是否正确
        if (!ValidationUtils.isValidPhoneNumber(tel))
        {
            return RespResult.fail("手机号格式不正确");
        }
        if (!ValidationUtils.isValidEmailAddress(email))
        {
            return  RespResult.fail("邮箱格式不正确");
        }


        //验证码是否正确?
        Map<String, Object> codeMap = (Map<String, Object>) session.getAttribute("EMAIL_CODE" + email);
        String emailCode = (String) codeMap.get("code");
        if (Assert.isEmpty(codeMap))
        {
            return RespResult.fail("请先获取验证码");
        }
        if (!code.equals(emailCode))
        {
            return RespResult.fail("验证失败");
        }
        else if (code.equals(emailCode))
        {
            session.removeAttribute("EMAIL_CODE" + email);

        }
        else
        {
            Date time = (Date) codeMap.get("time");
            if (System.currentTimeMillis() - time.getTime() > CURRENT_TIME)
            {
                session.removeAttribute("EMAIL_CODE" + email);
                return RespResult.fail("验证码已经失效");
            }
        }
        //验证账号是否为一
        User byUserAccount = userService.findUserByUserAccount(user);
        if (!Assert.isEmpty(byUserAccount))
        {
            return RespResult.fail("账号已经被注册");
        }
        //写入
        Date d = new Date();
        System.out.println(d);
        user.setCreateTime(d);
        user.setUpdateTime(d);
        if (userService.save(user))
        {
            return RespResult.success("恭喜你注册成功");
        }
        return RespResult.success("注册失败");
    }
    /**
     * 在每个子类方法调用之前先调用
     */
    @PostMapping("/login")
    //表示服务端返回JSON数据【异步一般都是以JSON数据格式进行交互】
    @ResponseBody
    public RespResult login(User user)
    {
        String userAccount = user.getUserAccount();
        String userPwd = user.getUserPwd();
//        if (Assert.isEmpty(userAccount))
//        {
//            return RespResult.fail("账户不能为空");
//        }
//
//        if (Assert.isEmpty(userPwd))
//        {
//            return RespResult.fail("密码不能为空");
//        }
        User byUserAccount = userService.findUserByUserAccount(user);

        if (Assert.isEmpty(byUserAccount))
        {
            return RespResult.fail("账号不存在");
        }
        if (!byUserAccount.getUserPwd().equals(userPwd))
        {
            return RespResult.fail("登录失败密码错误");
        }

        return RespResult.success("登录成功");
    }
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