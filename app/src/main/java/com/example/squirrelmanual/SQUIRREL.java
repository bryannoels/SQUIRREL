package com.example.squirrelmanual;

import android.app.Application;

public class SQUIRREL extends Application {
    private String name = "ABC";
    private int age = 19;
    private String phone = "12345678";

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

}