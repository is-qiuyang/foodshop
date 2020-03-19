package com.java.foodshop.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.java.foodshop.common.SzpJsonResult;
import com.java.foodshop.pojo.ToOrder;
import com.java.foodshop.request.OrderDeleteRequest;
import com.java.foodshop.request.OrderItemSubmitRequest;
import com.java.foodshop.request.OrderOneSubmitRequest;
import com.java.foodshop.response.ShowOrderResponse;
import com.java.foodshop.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    //提交订单操作
    @ApiOperation(value = "从购物车生成订单")
    @PostMapping("orderSubmit")
    public SzpJsonResult<String> orderSubmit(@RequestBody OrderItemSubmitRequest request) {
        //提交订单
        try {
            Integer integer = orderService.orderSubmit(request);
            if (integer>0){
                return SzpJsonResult.ok("成功添加"+integer+"个货物");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SzpJsonResult.errorMsg("添加失败");
    }

    //提交订单操作
    @ApiOperation(value = "从商品生成订单")
    @PostMapping("orderOneSubmit")
    public SzpJsonResult<String> orderOneSubmit(@RequestBody OrderOneSubmitRequest request) {
        //提交订单
        try {
            Integer integer = orderService.orderOneSubmit(request);
            if (integer==1){
                return SzpJsonResult.ok("成功添加");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SzpJsonResult.errorMsg("添加失败");
    }

    //展示订单列表详情
    @ApiOperation(value = "展示订单列表详情")
    @PostMapping("showOrder")
    public SzpJsonResult<List<ShowOrderResponse>> showOrder(Integer userId) {

        try {
            //根据当前用户的id查询出这个用户的所有订单信息
            List<ShowOrderResponse> orders=orderService.getOrdersByUseId(userId);
            return SzpJsonResult.ok(orders);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //跳转到订单详情页面
        return SzpJsonResult.errorMsg("您没有订单哦");
    }

    @ApiOperation(value = "删除订单")
    @PostMapping("orderDelete")
    public SzpJsonResult<String> orderDelete(@RequestBody OrderDeleteRequest request){
        List<Long> ids = request.getIds();
        Integer integer = orderService.orderDelete(ids,request.getUserId());
        if (integer>0){
            return SzpJsonResult.ok("成功删除"+integer+"个订单");
        }
        return SzpJsonResult.errorMsg("删除失败");
    }

}
