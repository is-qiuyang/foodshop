package com.java.foodshop.request;

import lombok.Data;

import javax.persistence.Column;

@Data
public class AddUserRequest {

    /**
     * 登录名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 性别(男1女2)
     */
    private Integer sex;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 地址
     */
    private String address;

    private String userImg;

    private Integer role ;

}
