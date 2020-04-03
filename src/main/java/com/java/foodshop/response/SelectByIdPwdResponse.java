package com.java.foodshop.response;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
public class SelectByIdPwdResponse {
    /**
     * 用户ID
     */
    @Column(name = "id")
    private Integer user_id;

    @Column(name = "nick_name")
    private String nick_name;

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
