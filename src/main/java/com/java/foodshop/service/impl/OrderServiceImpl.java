package com.java.foodshop.service.impl;

import com.java.foodshop.dao.ArticleDao;
import com.java.foodshop.dao.OrderItemDao;
import com.java.foodshop.dao.ShopCarDao;
import com.java.foodshop.dao.ToOrderDao;
import com.java.foodshop.pojo.*;
import com.java.foodshop.request.OrderItemRequest;
import com.java.foodshop.request.OrderItemSubmitRequest;
import com.java.foodshop.request.OrderOneSubmitRequest;
import com.java.foodshop.response.ShowOrderResponse;
import com.java.foodshop.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ToOrderDao toOrderDao;
    @Autowired
    private OrderItemDao orderItemDao;
    @Autowired
    private ShopCarDao shopCarDao;
    @Autowired
    private ArticleDao articleDao;

    //从购物车提交订单
    @Override
    @Transactional
    public Integer orderSubmit(OrderItemSubmitRequest request) {
        //第一步：封装订单对象
        ToOrder toOrder = new ToOrder();
        toOrder.setCreateTime(new Date());//指定下单时间
        toOrder.setUserId(request.getUserId());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");// 装订单编号orderCode，格式：PO-当前时间+用户id
        toOrder.setToOrderCode("PO-" + sdf.format(new Date()) + request.getUserId());//设置订单编号
        //定义订单总金额
        double totalPrice = 0.0;
        //得到articleIds
        List<Integer> articleIds = new ArrayList<>();
        List<OrderItemRequest> orderItems = request.getOrderItems();
        for (OrderItemRequest orderItem : orderItems) {
            Integer articleId = orderItem.getArticleId();
            articleIds.add(articleId);
        }
        //通过userId,articleIds查询出购物车里有的商品，将商品对应的orderNum,articleId添加进订单详情中去，顺便计算出所有商品的总价
        List<Shopcar> shopcars = shopCarDao.selectShopcars(request.getUserId(), articleIds);
        //更新商品的数量
        Map<Integer, Shopcar> articleMap = shopcars.stream().collect(Collectors.toMap(Shopcar::getArticleId,Shopcar -> Shopcar));
        List<Article> articleList = articleDao.selectArticles(articleIds);
        List<Article> articles = new ArrayList<>();
        for (Article article : articleList) {
            Article article1 = new Article();
            Integer id = article.getId();
            Integer articleNum = articleMap.get(id).getOrdernum();
            Integer storagy = article.getStoragy()-articleNum;
            if(storagy<0){
                return 0;
            }
            article1.setId(id);
            article1.setStoragy(storagy);
            articles.add(article1);
        }
        Integer integer = articleDao.updataArticleNum(articles);
        List<OrderItem> orderItemList = new ArrayList<>();
        for (int i = 0; i < shopcars.size(); i++) {
            OrderItem item = new OrderItem();
            item.setOrderNum(shopcars.get(i).getOrdernum());
            item.setArticleId(shopcars.get(i).getArticleId());
            orderItemList.add(item);
            totalPrice += shopcars.get(i).getConuntPrice();
        }
        //指定订单的总金额，也就是所有订单详情的总价
        toOrder.setAmount(totalPrice);
        //保存订单信息 ,保存完订单信息之后，需要获取订单的id，因为需要将订单的id存放在订单详情中
        int orderInt = toOrderDao.saveToOrder(toOrder);
        log.info("orderInt:{}",orderInt);

        //并将主键设置到订单详情对象中去
        for (OrderItem orderItem : orderItemList) {
            //获取订单主键的值
            int orderId = toOrder.getId();
            log.info("orderId:{}",orderId);
            orderItem.setOrderId(orderId);
        }
        Integer orderItemInt = orderItemDao.saveOrderItem(orderItemList);
        if(orderInt>=1 && orderItemInt>=1 && integer>=1){
            return Math.max(orderInt,orderItemInt);
        }
        return 0;
    }

    @Override
    public Integer orderOneSubmit(OrderOneSubmitRequest request) {
        //第一步：封装订单对象
        ToOrder toOrder = new ToOrder();
        toOrder.setCreateTime(new Date());//指定下单时间
        toOrder.setUserId(request.getUserId());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");// 装订单编号orderCode，格式：PO-当前时间+用户id
        toOrder.setToOrderCode("PO-" + sdf.format(new Date()) + request.getUserId());//设置订单编号
        //得到商品
        Article article = articleDao.selectArticleById(request.getArticleId());
        //更新商品数量
        List<Article> articles = new ArrayList<>();
        Article article1 = new Article();
        Integer id = article.getId();
        Integer articleNum = article.getStoragy();
        Integer storagy = articleNum-request.getOrdernum();
        article1.setId(id);
        article1.setStoragy(storagy);
        articles.add(article1);
        Integer integer = articleDao.updataArticleNum(articles);
        //定义订单总金额
        double totalPrice = article.getPrice()*article.getDiscont()*request.getOrdernum();
        toOrder.setAmount(totalPrice);
        int orderInt = toOrderDao.saveToOrder(toOrder);
        log.info("orderInt:{}",orderInt);
        //封装订单详情
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(toOrder.getId());
        orderItem.setArticleId(request.getArticleId());
        orderItem.setOrderNum(request.getOrdernum());
        Integer orderItemInt = orderItemDao.saveOneOrderItem(orderItem);
        if(orderInt==1 && orderItemInt==1 && integer>=1){
            return 1;
        }
        return 0;
    }

    @Override
    public List<ShowOrderResponse> getOrdersByUseId(Integer userId) {
        List<ToOrder> ordersByUseId = toOrderDao.getOrdersByUseId(userId);
        List<ShowOrderResponse> responses = new ArrayList<>();
        for (ToOrder toOrder : ordersByUseId) {
            ShowOrderResponse response = new ShowOrderResponse();
            BeanUtils.copyProperties(toOrder,response);
            responses.add(response);
        }
        return responses;
    }

    @Override
    public Integer orderDelete(List<Long> ids, Integer userId) {
        Integer toOrder = toOrderDao.orderDelete(ids, userId);
        Integer orderItem = orderItemDao.orderDelete(ids);
        if(toOrder>=1 && orderItem>=1 ){
            return Math.min(toOrder,orderItem);
        }
        return 0;
    }

}


