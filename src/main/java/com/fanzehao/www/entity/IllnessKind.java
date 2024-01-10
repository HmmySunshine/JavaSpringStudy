package com.fanzehao.www.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("illness_kind")
public class IllnessKind
{
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String info;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")  // 这个注解用于处理日期格式化
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")  // 这个注解用于处理日期格式化
    private Date updateTime;

}
