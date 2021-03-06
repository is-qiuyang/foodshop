package com.java.foodshop.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "to_order")
public class ToOrder {
    /**
     * 订单ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订单编号
     */
    @Column(name = "to_order_code")
    private String toOrderCode;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 总价
     */
    private Double amount;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 获取订单ID
     *
     * @return id - 订单ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置订单ID
     *
     * @param id 订单ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取订单编号
     *
     * @return to_order_code - 订单编号
     */
    public String getToOrderCode() {
        return toOrderCode;
    }

    /**
     * 设置订单编号
     *
     * @param toOrderCode 订单编号
     */
    public void setToOrderCode(String toOrderCode) {
        this.toOrderCode = toOrderCode == null ? null : toOrderCode.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取总价
     *
     * @return amount - 总价
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * 设置总价
     *
     * @param amount 总价
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}