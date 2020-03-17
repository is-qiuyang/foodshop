package com.java.foodshop.pojo;

import javax.persistence.*;

@Table(name = "shopcar")
public class Shopcar {
    /**
     * 商品数量
     */
    private Integer ordernum;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "article_id")
    private Integer articleId;

    /**
     * 总价
     */
    @Column(name = "conunt_price")
    private Double conuntPrice;

    /**
     * 获取商品数量
     *
     * @return ordernum - 商品数量
     */
    public Integer getOrdernum() {
        return ordernum;
    }

    /**
     * 设置商品数量
     *
     * @param ordernum 商品数量
     */
    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return article_id
     */
    public Integer getArticleId() {
        return articleId;
    }

    /**
     * @param articleId
     */
    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    /**
     * 获取总价
     *
     * @return conunt_price - 总价
     */
    public Double getConuntPrice() {
        return conuntPrice;
    }

    /**
     * 设置总价
     *
     * @param conuntPrice 总价
     */
    public void setConuntPrice(Double conuntPrice) {
        this.conuntPrice = conuntPrice;
    }
}