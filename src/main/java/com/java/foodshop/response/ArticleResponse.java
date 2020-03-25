package com.java.foodshop.response;

import lombok.Data;

@Data
public class ArticleResponse {
    private Integer id;
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

    /**
     * 产地
     */
    private String locality;

    /**
     * 存储数量
     */
    private Integer storagy;

    /**
     * 商品图片
     */
    private String image;

    /**
     * 描述
     */
    private String description;
}
