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
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("medicine")
public class Medicine {
    @TableId(type = IdType.AUTO)
    private int id;
    private String medicineName;
    private String keyword;
    private String medicineEffect;
    private String medicineBrand;
    private String interaction;
    private String taboo;
    private String usAge;
    private int medicineType;
    private String imgPath;
    private double medicinePrice;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")  // 这个注解用于处理日期格式化
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")  // 这个注解用于处理日期格式化
    private Date updateTime;

}
