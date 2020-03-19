package com.java.foodshop.request;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
public class OrderItemRequest {
    /**
     * 商品ID
     */
    @Column(name = "article_id")
    private Integer articleId;


}
