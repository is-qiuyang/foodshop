package com.java.foodshop.controller;

import com.java.foodshop.common.SzpJsonResult;
import com.java.foodshop.pojo.User;
import com.java.foodshop.request.AddUserRequest;
import com.java.foodshop.request.UpdataUserRequest;
import com.java.foodshop.response.SelectUserResponse;
import com.java.foodshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 方法描述
     * @ 用户注册,写个方法对电话号码进行查重
     * @return
     * @date 2020/2/29
     */
    @PostMapping("add/user")
    public SzpJsonResult<Integer> addUserByJson(@RequestBody AddUserRequest adduserRequest) {
        return SzpJsonResult.ok(userService.addUser(adduserRequest));
    }

    /**
     * 方法描述
     * @ 用户通过电话号码和密码登录
     * @return
     * @date 2020/2/29
     */
    @GetMapping("login")
    public SzpJsonResult<String> login(@RequestParam(value = "phone", required = true) Long phone,
                                     @RequestParam(value = "password", required = true) String password) {
        User user = userService.login(phone, password);
        if (user != null) {
            //身份判断
            Integer role = user.getRole();
            if (role == 0) {
                return  SzpJsonResult.ok("管理员登录");
            } else if (role == 2) {
                return  SzpJsonResult.ok("商家登录");
            } else if (role == 1) {
                return  SzpJsonResult.ok("用户登录");
            } else {
                return SzpJsonResult.errorMsg("身份验证失败请重新登录");
            }
        } else {
            return SzpJsonResult.errorMsg("账号或密码错误，请重新输入");
        }
    }

    /**
     * 方法描述
     * @ 用户注销，这个太简单了，最好加个校验
     * @return
     * @date 2020/2/29
     */
    @DeleteMapping("delete/user")
    public SzpJsonResult<Integer> delUserByJson(@RequestParam(value = "id", required = true) Integer id) {
        return SzpJsonResult.ok(userService.delUser(id));
    }

    /**
     * 方法描述
     * @ 更新用户信息
     * @return
     * @date 2020/2/29
     */
    @PutMapping("update/user")
    public SzpJsonResult<String> updUserByJson(@RequestBody UpdataUserRequest userRequest, Long id) {
        boolean isSuccess = userService.updateUserById(userRequest, id);
        return SzpJsonResult.ok(isSuccess);
    }

    /**
     * 方法描述
     * @ 查找所有用户，只有管理员才有权限
     * @return
     * @date 2020/2/29
     */
    @GetMapping("select/allUser")
    public SzpJsonResult<SelectUserResponse> selectAllUser(Long id){
        Integer role = userService.selectRoleById(id).getRole();
        if(role == 0){
           List<User> users = userService.selectAllUser();
           return SzpJsonResult.ok(users);
        }else {
            return SzpJsonResult.errorMsg("您没有权限");
        }
    }

}