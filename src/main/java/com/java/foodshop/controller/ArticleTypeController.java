package com.java.foodshop.controller;

import com.java.foodshop.common.SzpJsonResult;
import com.java.foodshop.pojo.ArticleType;
import com.java.foodshop.request.ListIds;
import com.java.foodshop.response.ArticleTypeResponse;
import com.java.foodshop.service.ArticleTypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleTypeController {
    @Autowired
    private ArticleTypeService articleTypeService;

    /**
     * 方法描述
     * @ 添加零食类，不允许重复
     * @return Integer
     * @date 2020/3/13
     */
    @PostMapping(value = "add/articleType")
    @ApiOperation(value = "添加零食类，不允许重复,1添加成功，0添加失败，-1已有这个类")
    public SzpJsonResult<Integer> addArticleType(@RequestBody String name){
        return SzpJsonResult.ok(articleTypeService.addArticleType(name));
    }

    /**
     * 方法描述
     * @ 删除零食类
     * @return
     * @date 2020/3/13
     */
    @PostMapping(value = "delete/articleType")
    @ApiOperation(value = "删除零食类")
    public SzpJsonResult<String> delArticleType(@RequestBody ListIds listIds){
        List<Long> ids = listIds.getIds();
        Integer integer = articleTypeService.delArticleType(ids);
        if (integer>0){
            return SzpJsonResult.ok("成功删除"+integer+"个零食种类");
        }
        return SzpJsonResult.ok("删除失败");
    }


    @PutMapping(value = "update/articleType")
    public SzpJsonResult<Integer> updArticleType(@RequestBody ArticleType articleType){
        return SzpJsonResult.ok(articleTypeService.updArticleType(articleType));
    }

    @GetMapping(value = "get/all/articleType")
    public SzpJsonResult<ArticleTypeResponse> getAllArticleType(){
        return SzpJsonResult.ok(articleTypeService.selectAllArticleType());
    }
}
