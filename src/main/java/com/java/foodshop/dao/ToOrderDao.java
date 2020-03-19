package com.java.foodshop.dao;

import com.java.foodshop.mapper.ToOrderMapper;
import com.java.foodshop.pojo.ToOrder;
import org.apache.ibatis.annotations.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository
public class ToOrderDao {
    @Autowired
    private ToOrderMapper orderMapper;

   @Options(useGeneratedKeys = true, keyProperty = "id")
    public Integer saveToOrder(ToOrder order) {
        int insert = orderMapper.insert(order);
        return insert;
    }

    public List<ToOrder> getOrdersByUseId(Integer userId) {
        Example example = new Example(ToOrder.class);
        example.createCriteria().andEqualTo("userId",userId);
        return orderMapper.selectByExample(example);
    }

    public Integer orderDelete(List<Long> ids, Integer userId) {
        Example example = new Example(ToOrder.class);
        example.createCriteria().andEqualTo("userId",userId).andIn("id",ids);
        return orderMapper.deleteByExample(example);
    }
}
