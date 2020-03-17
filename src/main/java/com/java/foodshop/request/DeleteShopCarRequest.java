package com.java.foodshop.request;

import lombok.Data;

import java.util.List;
@Data
public class DeleteShopCarRequest {
    private int userId;

    private List<Long> articleIds;
}
