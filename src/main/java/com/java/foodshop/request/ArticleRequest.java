package com.java.foodshop.request;

import lombok.Data;

import javax.persistence.Column;

@Data
public class ArticleRequest {
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

    /**
     * 类型ID
     */
    @Column(name = "type_ID")
    private String typeId;

}
