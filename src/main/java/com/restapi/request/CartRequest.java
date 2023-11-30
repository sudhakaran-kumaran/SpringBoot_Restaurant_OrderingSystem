package com.restapi.request;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartRequest {
    private Long userId;
    private String dishTitle;
    private Integer count;
    private Double price;
    private Long dishId;
}





















