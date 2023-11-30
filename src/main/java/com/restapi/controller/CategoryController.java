package com.restapi.controller;

import com.restapi.model.Category;
import com.restapi.response.CategoryResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user/category")
@PreAuthorize("hasRole('ROLE_USER')")
public class CategoryController {
    @Autowired
    private APIResponse apiResponse;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllCategories(){
        CategoryResponse categoryResponse=categoryService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(categoryResponse.getCategories());
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<APIResponse> getUserCategory(@PathVariable Long categoryId){
        Optional<Category> categoryResponse=categoryService.findById(categoryId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(categoryResponse);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

}
