package com.fanzehao.www.vo.response;

public class Student {
    private String stuId;
    private String name;

    public Student(String stuId, String name) {
        this.stuId = stuId;
        this.name = name;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " " + stuId;
    }
}
