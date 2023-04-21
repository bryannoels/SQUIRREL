package com.example.squirrelmanual;

import android.app.Application;

public class SQUIRREL extends Application {
    private String name = "Bryan";
    private int age = 19;
    private String phone = "81234567";
    private String password = "password";
    private int studentId = 1;
    private int moduleId = 1;
    public static String baseURL = "http://192.168.10.137:9999/backend";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getModuleId() {
        return moduleId;
    }
    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

}