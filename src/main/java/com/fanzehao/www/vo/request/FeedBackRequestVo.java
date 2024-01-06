package com.fanzehao.www.vo.request;

import org.springframework.web.bind.annotation.RequestParam;

public class FeedBackRequestVo {
    private String name;
    private String email;
    private String title;
    private String content;

    @Override
    public String toString() {
        return "FeedBackRequestVo{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';

    }

    public String getName() {
        return name;
    }

    public void setName(@RequestParam(name = "nickname")String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
