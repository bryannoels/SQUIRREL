package com.example.squirrelmanual;

import android.app.Application;

public class SQUIRREL extends Application {
    private String name = "Bryan";
    private int age = 19;
    private String phone = "81234567";
    private String password = "password";
    private int studentId = 1;
    private int moduleId = 1;
    private String moduleName = "Object Oriented Programming";
    private int levelNumber = 1;
    private int questionNumber = 1;
    private int currentAnswer = 1;
    public static String baseURL = "http://192.168.0.101:9999/backend";

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

    public String getModuleName() {
        return moduleName;
    }
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }
    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public int getLevelNumber() {
        return levelNumber;
    }
    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public int getCurrentAnswer() {
        return currentAnswer;
    }
    public void setCurrentAnswer(int currentAnswer) {
        this.currentAnswer = currentAnswer;
    }

}