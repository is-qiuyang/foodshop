package com.java.foodshop.pojo;

import javax.persistence.*;

@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订单ID
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 商品ID
     */
    @Column(name = "article_id")
    private Integer articleId;

    /**
     * 订单数量
     */
    @Column(name = "order_num")
    private Integer orderNum;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

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