package com.java.foodshop.request;

import lombok.Data;

@Data
public class SelectByIdPwdRequest {
    private String userName;
    private String password;
}
