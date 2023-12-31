package com.restapi.response;

import com.restapi.model.Dish;
import com.restapi.request.DishRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DishResponse {
    private Long id;
    private Long categoryId;
    private String title;
    private String description;
    private Double price;
    private String photo;
}
