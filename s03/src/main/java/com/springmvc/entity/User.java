package com.springmvc.entity;

/**
 * @author: lijinhao
 * @date: 2022/03/01 21:20
 */

public class User {
    private String username;
    private Long password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getPassword() {
        return password;
    }

    public void setPassword(Long password) {
        this.password = password;
    }
}
