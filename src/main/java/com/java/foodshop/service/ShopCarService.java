package com.java.foodshop.service;

import com.java.foodshop.request.AddShopCarRequest;
import com.java.foodshop.response.ShowShopCarsResponse;

import java.util.List;

public interface ShopCarService {

    List<ShowShopCarsResponse> getAllShopCarByUserId(int userId);

    Integer addArticleToShopCar(AddShopCarRequest request);

    Integer updateShopcar(int userId,int articleId, int ordernum);

    Integer deleteShopcar(int userId, List<Long> articleIds);
}
