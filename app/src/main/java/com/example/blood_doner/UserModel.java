package com.example.blood_doner;

public class UserModel {
    private String username,email,phone,gender,blood_group;

    UserModel(){

    }

    public UserModel(String username, String email, String phone, String gender, String blood_group) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.blood_group = blood_group;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }
}
