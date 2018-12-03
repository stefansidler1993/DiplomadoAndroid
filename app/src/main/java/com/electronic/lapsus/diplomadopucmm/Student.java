package com.electronic.lapsus.diplomadopucmm;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class Student implements Serializable {

    public static final String TABLE_NAME = "students";
    public static final String COLUMN_IMGID = "imgID";
    public static final String COLUMN_NAME= "name";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_BIRTHDAY = "birthday";
    public static final String COLUMN_CAREER = "career";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_ASSISTANCE = "assistance";

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_IMGID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_GENDER + " TEXT,"
                    + COLUMN_BIRTHDAY + " DATETIME DEFAULT CURRENT_TIMESTAMP,"
                    + COLUMN_CAREER + " TEXT,"
                    + COLUMN_ADDRESS + " TEXT,"
                    + COLUMN_ASSISTANCE + " BOOLEAN"
                    + ")";

    int imgID;
    String name;
    String gender;
    LocalDateTime birthday;
    String career;
    String address;
    boolean assistance;

    public Student() {

    }

    public Student(int imgID, String name, String gender, LocalDateTime birthday, String career, String address, boolean assistance) {
        this.imgID = imgID;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.career = career;
        this.address = address;
        this.assistance = assistance;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isAssistance() {
        return assistance;
    }

    public void setAssistance(boolean assistance) {
        this.assistance = assistance;
    }
}
