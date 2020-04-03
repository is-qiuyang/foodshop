package com.java.foodshop.service;

import com.java.foodshop.pojo.User;
import com.java.foodshop.request.UpdataUserRequest;

public interface UserService {

    //用户登录
    User selectByIdPwd(String loginname, String pwd);

    //用户注册
    Integer insertUser(User user);

    int updateLastLoginTime(User user);

    Integer deleteUserById(Long id);

    boolean updateUserById(UpdataUserRequest userRequest);

    User selectById(Long id);
}
