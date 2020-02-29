package com.java.foodshop.pojo;

import javax.persistence.*;

@Table(name = "order_item")
public class OrderItem {
    /**
     * 订单ID
     */
    @Id
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 商品ID
     */
    @Id
    @Column(name = "article_id")
    private Integer articleId;

    /**
     * 订单数量
     */
    @Column(name = "order_num")
    private Integer orderNum;

    /**
     * 获取订单ID
     *
     * @return order_id - 订单ID
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置订单ID
     *
     * @param orderId 订单ID
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取商品ID
     *
     * @return article_id - 商品ID
     */
    public Integer getArticleId() {
        return articleId;
    }

    /**
     * 设置商品ID
     *
     * @param articleId 商品ID
     */
    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    /**
     * 获取订单数量
     *
     * @return order_num - 订单数量
     */
    public Integer getOrderNum() {
        return orderNum;
    }

    /**
     * 设置订单数量
     *
     * @param orderNum 订单数量
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
}