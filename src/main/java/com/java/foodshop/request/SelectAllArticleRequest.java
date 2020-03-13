package com.java.foodshop.request;

import lombok.Data;

@Data
public class SelectAllArticleRequest {
    Integer pageSize=10;
    Integer pageNumber;
}
