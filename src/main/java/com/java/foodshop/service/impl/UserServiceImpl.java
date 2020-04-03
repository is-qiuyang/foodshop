package com.java.foodshop.service.impl;


import com.java.foodshop.dao.UserDao;
import com.java.foodshop.pojo.User;
import com.java.foodshop.request.UpdataUserRequest;
import com.java.foodshop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    //根据ID和密码查询用户是否存在，用户登录
    @Override
    public User selectByIdPwd(String loginname, String pwd) {
        List<User> users = userDao.selectIdentityByIdPwd(loginname, pwd);
        for (User userList:users) {
            if(userList.getLoginName().equals(loginname) && userList.getPassword().equals(pwd)){
                return userList;
            }
        }
        return null;
    }

    //用户注册
    @Override
    public Integer insertUser(User user) {
        return userDao.insertUser(user);
    }

    /**
     * 方法描述
     * @ 更新用户最后登陆时间
     * @return
     * @date 2020/2/12
     */
    @Override
    public int updateLastLoginTime(User user) {
        return userDao.updateLastLoginTime(user);
    }

    /**
     * 方法描述
     * @ 删除用户
     * @return
     * @date 2020/2/12
     */
    @Override
    public Integer deleteUserById(Long id) {
        if(StringUtils.isEmpty(id)){
            return -1;
        }else {
            return userDao.deleteUserById(id);
        }
    }

    @Override
    public boolean updateUserById(UpdataUserRequest userRequest) {
        User user = new User();
        BeanUtils.copyProperties(userRequest,user);
        log.info("user",user);
        return userDao.updateUserById(user);
    }

    @Override
    public User selectById(Long id) {

        return userDao.selectById(id);
    }


}
