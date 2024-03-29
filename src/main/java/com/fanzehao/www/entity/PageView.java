package com.fanzehao.www.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("pageview")
public class PageView {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("pageViews")
    private Integer pageViews;
    private Integer illnessId;
}
