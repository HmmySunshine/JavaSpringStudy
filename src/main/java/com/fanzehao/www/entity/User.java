package com.fanzehao.www.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@TableName("user")
public class User {
    @TableId(type = IdType.AUTO)
    private int id;

    private String userAccount;
    private String userName;
    private String userPwd;
    private Integer userAge;
    private String userSex;  // 将属性名更新为与数据库列名一致
    private String userEmail;
    private String userTel;
    private Integer roleStatus;
    private String imgPath;
    //hour minutes seconds

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")  // 这个注解用于处理日期格式化
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")  // 这个注解用于处理日期格式化
    private Date updateTime;


}
