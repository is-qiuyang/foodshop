package com.java.foodshop.response;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class ShowOrderResponse {
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

}
