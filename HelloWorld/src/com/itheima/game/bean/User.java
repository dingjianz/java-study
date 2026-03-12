package com.itheima.game.bean;

import java.nio.file.attribute.AclFileAttributeView;

public class User {
    private String id; // 用户ID iflytek + 5位数字 iflytek12345
    private String username; // 用户名
    private String password; // 用户密码
    private boolean status; // 状态 false 禁止登录 true 允许登录

    public User() {
    }

    public User(String id, String username, String password, boolean status) {
        this.id = createId();
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String createId() {
        return "iflytek" + String.format("%05d", (int) (Math.random() * 100000));
    }
}
