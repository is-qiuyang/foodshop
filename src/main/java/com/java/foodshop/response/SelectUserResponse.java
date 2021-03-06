package com.java.foodshop.response;

import lombok.Data;

import javax.persistence.Column;

@Data
public class SelectUserResponse {

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
    private String emall;

    /**
     * 电话
     */
    private String phone;

    /**
     * 地址
     */
    private String address;
}
