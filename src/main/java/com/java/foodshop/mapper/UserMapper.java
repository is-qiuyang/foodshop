package com.java.foodshop.mapper;

import com.java.foodshop.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends CommonMapper<User> {
}