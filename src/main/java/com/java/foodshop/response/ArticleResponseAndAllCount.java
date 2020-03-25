package com.java.foodshop.response;

import lombok.Data;

import java.util.List;

@Data
public class ArticleResponseAndAllCount {
    private List<ArticleResponse> articleResponses;
    private Long allCount;
}
