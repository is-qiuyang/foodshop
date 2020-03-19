package com.java.foodshop.request;

import lombok.Data;

import javax.persistence.Column;
import java.util.List;

@Data
public class OrderDeleteRequest {
    private List<Long> ids;
    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Integer userId;
}
