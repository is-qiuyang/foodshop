package com.java.foodshop.controller;

import com.java.foodshop.common.SzpJsonResult;
import com.java.foodshop.request.AddShopCarRequest;
import com.java.foodshop.request.DeleteShopCarRequest;
import com.java.foodshop.response.ShowShopCarsResponse;
import com.java.foodshop.service.ShopCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopCar")
public class ShopCarController {

    @Autowired
    private ShopCarService shopCarService;

    /**
     * 添加商品至购物车
     * @return
     */
    @PostMapping("/addToCar")
    public SzpJsonResult<String> addShopCar(@RequestBody AddShopCarRequest request) {
        //根据用户信息，商品id，已存在数量来添加商品至购物车
        Integer num = shopCarService.addArticleToShopCar(request);
        if(num==1){
            return SzpJsonResult.ok("添加购物车成功");
        }
        return SzpJsonResult.errorMsg("添加购物车失败");
    }

    //展示购物车详情
    @GetMapping("/showShopCar")
    public SzpJsonResult<List<ShowShopCarsResponse>> showShopCars(int userId) {

        return SzpJsonResult.ok(shopCarService.getAllShopCarByUserId(userId));
    }


    /**
     * 更新购物车中商品信息，实现加减法
     * @return
     */
    @PutMapping("/updateShopcar")
    public SzpJsonResult<String> updateShopcar(@RequestBody AddShopCarRequest request) {
        //更新购物车中商品数量
        return SzpJsonResult.ok(shopCarService.updateShopcar(request.getUserId(),request.getArticleId(),request.getOrdernum()));

    }

    //删除购物车中商品的信息
    @PostMapping("/deleteShopCar")
    public SzpJsonResult<String> deleteShopCar(@RequestBody DeleteShopCarRequest request) {
        //删除购物车中的商品
        Integer integer = shopCarService.deleteShopcar(request.getUserId(), request.getArticleIds());

        if (integer>0){
            return SzpJsonResult.ok("成功删除"+integer+"个章节");
        }
        return SzpJsonResult.ok("删除失败");
    }

}
