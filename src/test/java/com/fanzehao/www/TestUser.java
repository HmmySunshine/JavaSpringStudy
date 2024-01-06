package com.fanzehao.www;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanzehao.www.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.fanzehao.www.mapper.UserMapper;
import com.fanzehao.www.entity.User;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class TestUser {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Test
    public void testSelect()
    {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        //Assert.isTrue(1 == userList.size(), "");
        userList.forEach(System.out::println);
    }

    @Test
    public void testSave()
    {
        User user = new User();
        user.setUserName("fafa");
        user.setUserEmail("123");
        user.setUserAge(16);
        user.setUserSex("男");
        user.setUserTel("134");
        user.setRoleStatus(1);
        user.setImgPath("www.123.com");
        user.setUserPwd("1234");
        user.setCreateTime(new Date());

//        User user1 = User.builder().id(4).userName("hehe").userPwd("1234").userAge(12).build();
//        userService.save(user1);
//
        User user2 = User.builder().id(2).userName("xiaohu").userPwd("1234").userAge(16).userAccount("user").build();
        User updateUser = User.builder().id(2).userAge(17).build();
        // 执行更新操作
        System.out.println(userService.updateById(updateUser));
        System.out.println(userService.getById(2));


        System.out.println(userService.removeById(2));

        List<User> userList = userService.list();
        userList.forEach(System.out::println);
        //userMapper.insert(user);
        User user3 = User.builder().id(4).userName("xiaoming").userPwd("6666").userAge(18).userAccount("user").build();
        userMapper.insert(user3);
        userMapper.updateById(user3); //
        userMapper.selectById(4); // 根据主键查询
        //userMapper.deleteById(4);




       //System.out.println(userService.updateById(user));
        //
//  查找   List<User> userList = userService.list();
//        userList.forEach(System.out::println);
//        IPage<User> userIPage = userService.page(new Page<User>());
//        System.out.println(userIPage);
//
//        List<User> records = userIPage.getRecords();
//        System.out.println("size" + userIPage.getSize());
//        System.out.println("current" + userIPage.getCurrent());
//        System.out.println(userIPage.getTotal());
//        System.out.println(userIPage.offset());

        //每页数据2条，当前第2页
//        Page<User> userPage=new Page<>(2,2);
//
//        IPage<User> userIPage2=userService.page(userPage);
//        List<User> records2=userIPage2.getRecords();
//        records2.forEach(System.out::println);
//        System.out.println("getSize"+userIPage2.getSize());//10          每页数据的记录数
//        System.out.println("getCurrent"+userIPage2.getCurrent());//1      当前是第几页
//        System.out.println("getTotal"+userIPage2.getTotal());//0            总行数
//        System.out.println("offset"+userIPage2.offset());//0                 limit的偏离值
//        System.out.println("getPages"+userIPage2.getPages());//0
    }
    @Test
    public void testWrappper(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("id",1);
        User one = userService.getOne(userQueryWrapper);
        System.out.println(one);

    }
    @Test
    public void testWrappper02(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_account","fafa");
        userQueryWrapper.eq("user_pwd","1234");
        User one = userService.getOne(userQueryWrapper);
        System.out.println(one);

    }
    @Test
    public void testLambda()
    {
        System.out.println("lambda");
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserAccount, "1");
        lambdaQueryWrapper.eq(User::getUserPwd, "1");
        User one = userService.getOne(lambdaQueryWrapper);
        System.out.println(one);
    }
}
