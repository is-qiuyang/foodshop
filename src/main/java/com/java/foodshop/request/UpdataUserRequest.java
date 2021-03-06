package com.java.foodshop.request;

import lombok.Data;

import javax.persistence.Column;

@Data
public class UpdataUserRequest {
    private Integer id;
    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    @Column(name = "nick_name")
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

    /**
     * 用户头像
     */
    private String avatarurl;

}
