package com.example.mountaincl.helper;

import java.io.Serializable;

public class User implements Serializable {
    private String username, email, phone, password;
    private int id;
    public User(int id, String username, String phone, String email) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.email = email;
    }
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {return phone; }

    public int getId() {
        return id;
    }

}
