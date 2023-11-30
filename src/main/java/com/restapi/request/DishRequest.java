package com.restapi.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishRequest {
    private Long id;
    private Long categoryId;
    private String title;
    private String description;
    private Double price;
    private String photo;

}