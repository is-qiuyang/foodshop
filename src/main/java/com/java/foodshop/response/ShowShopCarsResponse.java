package com.java.foodshop.response;

import lombok.Data;

import javax.persistence.Column;

@Data
public class ShowShopCarsResponse {

    private ShowShopCarsArticleResponse articleResponse;

    /**
     * 商品数量
     */
    private Integer ordernum;

    /**
     * 总价
     */
    @Column(name = "conunt_price")
    private Double conuntPrice;

}
