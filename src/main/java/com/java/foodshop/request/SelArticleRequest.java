package com.java.foodshop.request;

import lombok.Data;

@Data
public class SelArticleRequest {
    String title;
    Integer pageSize = 10;
    Integer pageNumber ;
}
