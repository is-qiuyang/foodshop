package com.java.foodshop.response;

import lombok.Data;

@Data
public class ShowShopCarsArticleResponse {

    /**
     * 商品名
     */
    private String title;

    /**
     * 供应商
     */
    private String supplier;

    /**
     * 商品价格
     */
    private Double price;

    /**
     * 折扣
     */
    private Double discont;
}
