package com.java.foodshop.request;

import lombok.Data;

import java.util.List;
@Data
public class OrderItemSubmitRequest {
    Integer userId;
    private List<OrderItemRequest> orderItems;
}
