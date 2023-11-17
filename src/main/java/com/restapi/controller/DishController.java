package com.restapi.controller;

import com.restapi.dto.DishDto;
import com.restapi.model.Dish;
import com.restapi.repository.CategoryRepository;
import com.restapi.response.DishResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dish")
@PreAuthorize("hasRole('ROLE_USER')")
public class DishController {

    @Autowired
    private APIResponse apiResponse;
    @Autowired
    private DishService dishService;
    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllDishes() {
        DishResponse dishList =dishService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(dishList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
