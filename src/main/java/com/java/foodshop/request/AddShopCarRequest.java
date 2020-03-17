package com.java.foodshop.request;

import lombok.Data;

import javax.persistence.Column;

@Data
public class AddShopCarRequest {

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "article_id")
    private Integer articleId;

    /**
     * 商品数量
     */
    private Integer ordernum;
}
