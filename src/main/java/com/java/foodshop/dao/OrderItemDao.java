package com.java.foodshop.dao;

import com.java.foodshop.mapper.OrderItemMapper;
import com.java.foodshop.pojo.OrderItem;
import com.java.foodshop.pojo.ToOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository
public class OrderItemDao {
    @Autowired
    private OrderItemMapper orderItemMapper;

    public Integer orderDelete(List<Long> ids) {
        Example example = new Example(OrderItem.class);
        example.createCriteria().andIn("orderId",ids);
        return orderItemMapper.deleteByExample(example);
    }

    public Integer saveOrderItem(List<OrderItem> orderItemList) {
        return orderItemMapper.insertList(orderItemList);
    }

    public Integer saveOneOrderItem(OrderItem orderItem) {
        return orderItemMapper.insert(orderItem);
    }
}
