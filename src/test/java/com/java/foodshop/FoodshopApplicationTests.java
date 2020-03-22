package com.java.foodshop;

import com.java.foodshop.dao.OrderItemDao;
import com.java.foodshop.dao.ToOrderDao;
import com.java.foodshop.pojo.OrderItem;
import com.java.foodshop.pojo.ToOrder;
import com.java.foodshop.response.ShowShopCarsResponse;
import com.java.foodshop.service.ShopCarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@MapperScan(basePackages = { "com.java.foodshop.dao" })
class FoodshopApplicationTests {
    @Autowired
    private com.java.foodshop.dao.OrderItemDao OrderItemDao;
    @Autowired
    private ToOrderDao toOrderDao;
    @Autowired
    private ShopCarService shopCarService;

    @Test
    public void insertToOrderItem(){
        List<OrderItem> ToOrderItems = new ArrayList<>();
        OrderItem ToOrderItem1 = new OrderItem();
        ToOrderItem1.setOrderId(1);
        ToOrderItem1.setArticleId(1);
        ToOrderItem1.setOrderNum(3);
        ToOrderItems.add(ToOrderItem1);
        OrderItem ToOrderItem2 = new OrderItem();
        ToOrderItem2.setOrderId(2);
        ToOrderItem2.setArticleId(2);
        ToOrderItem2.setOrderNum(3);
        ToOrderItems.add(ToOrderItem2);
        Integer integer = OrderItemDao.saveOrderItem(ToOrderItems);
        System.out.println(integer);
    }

    @Test
    public void insertToOrder(){
        ToOrder ToOrder = new ToOrder();
        ToOrder.setId(1);
        ToOrder.setCreateTime(new Date());
        ToOrder.setAmount(43.5);
        ToOrder.setToOrderCode("kahbxgcn");
        ToOrder.setUserId(1);
        int i = toOrderDao.saveToOrder(ToOrder);
        System.out.println(i);
    }

    @Test
    public void showShopCar(){
        List<ShowShopCarsResponse> allShopCarByUserId = shopCarService.getAllShopCarByUserId(1);
        System.out.println(allShopCarByUserId.toString());
    }

}
