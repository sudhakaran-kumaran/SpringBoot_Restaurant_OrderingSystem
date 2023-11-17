package com.restapi.request;

import io.swagger.models.auth.In;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartRequest {
    private Long userId;
    private Long dishId;
    private Integer count;
}
