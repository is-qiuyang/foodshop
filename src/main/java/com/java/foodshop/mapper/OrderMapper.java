package com.java.foodshop.mapper;

import com.java.foodshop.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends CommonMapper<Order> {
}