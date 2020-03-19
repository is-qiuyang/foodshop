package com.java.foodshop.request;

import lombok.Data;

import javax.persistence.Column;

@Data
public class OrderOneSubmitRequest {
    Integer userId;
    @Column(name = "article_id")
    private Integer articleId;
    /**
     * 商品数量
     */
    private Integer ordernum;
}
