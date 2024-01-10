package com.fanzehao.www.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("illness")
public class Illness {
    @TableId(type = IdType.AUTO)
    public Integer id;

    public int kindId;
    public String illnessName;
    public String includeReason;
    public String illnessSymptom;
    public String specialSymptom;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")  // 这个注解用于处理日期格式化
    public Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")  // 这个注解用于处理日期格式化
    public Date updateTime;

    @TableField(exist = false)
    private String kindName;

    @TableField(exist = false)
    private Integer pageView;

}
