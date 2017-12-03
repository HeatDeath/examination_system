package com.system.pojo;

/**
 * teacher扩展类
 */
public class TeacherCustom extends Teacher {
    //所属院系名
    private String collegeName;

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }
}
