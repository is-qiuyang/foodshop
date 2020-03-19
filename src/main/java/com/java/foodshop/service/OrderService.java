package com.java.foodshop.service;

import com.java.foodshop.pojo.ToOrder;
import com.java.foodshop.request.OrderItemSubmitRequest;
import com.java.foodshop.request.OrderOneSubmitRequest;
import com.java.foodshop.response.ShowOrderResponse;

import java.util.List;

public interface OrderService {
    //提交订单
    Integer orderSubmit(OrderItemSubmitRequest request);

    Integer orderOneSubmit(OrderOneSubmitRequest request);

    List<ShowOrderResponse> getOrdersByUseId(Integer userId);

    Integer orderDelete(List<Long> ids, Integer userId);

    //展示订单详情页面
  //  List<Order> getOrdersByUseId(HttpSession session);
}
