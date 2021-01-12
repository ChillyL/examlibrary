package com.chilly.examlibrary.entity;

public class Admin {
    private Long admin_id;
    private String admin_number;  //管理员账号
    private String admin_name;  //名字
    private String admin_password;  //密码

    public Long getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Long admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_number() {
        return admin_number;
    }

    public void setAdmin_number(String admin_number) {
        this.admin_number = admin_number;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getAdmin_password() {
        return admin_password;
    }

    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "admin_id=" + admin_id +
                ", admin_number='" + admin_number + '\'' +
                ", admin_name='" + admin_name + '\'' +
                ", admin_password='" + admin_password + '\'' +
                '}';
    }
}
