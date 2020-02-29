package com.java.foodshop.pojo;

import javax.persistence.*;

@Table(name = "shopcar")
public class Shopcar {
    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Id
    @Column(name = "article_id")
    private Integer articleId;

    /**
     * 订单数量
     */
    private Integer ordernum;

    /**
     * 总价
     */
    @Column(name = "conunt_price")
    private Long conuntPrice;

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
     * 获取订单数量
     *
     * @return ordernum - 订单数量
     */
    public Integer getOrdernum() {
        return ordernum;
    }

    /**
     * 设置订单数量
     *
     * @param ordernum 订单数量
     */
    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    /**
     * 获取总价
     *
     * @return conunt_price - 总价
     */
    public Long getConuntPrice() {
        return conuntPrice;
    }

    /**
     * 设置总价
     *
     * @param conuntPrice 总价
     */
    public void setConuntPrice(Long conuntPrice) {
        this.conuntPrice = conuntPrice;
    }
}