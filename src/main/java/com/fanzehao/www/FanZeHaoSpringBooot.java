package com.fanzehao.www;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@MapperScan("com.fanzehao.www.mapper")
public class FanZeHaoSpringBooot {
    public static void main(String[] args) {
        SpringApplication.run(FanZeHaoSpringBooot.class, args);

    }
}
