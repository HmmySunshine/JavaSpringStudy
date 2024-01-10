package com.fanzehao.www.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.lang.reflect.Type;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("feedback")
public class FeedBack {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;
    private String title;
    private String email;
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")  // 这个注解用于处理日期格式化
    public Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")  // 这个注解用于处理日期格式化
    public Date updateTime;
}
