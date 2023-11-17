package com.restapi.controller;

import com.restapi.response.common.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@PreAuthorize("hasRole('ROLE_USER')")
public class CartController {
    @Autowired
    private APIResponse apiResponse;
    @GetMapping("/{userId]")
    public ResponseEntity<APIResponse> getUserCart(@PathVariable Integer userId){
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<APIResponse> addtoCart(){
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<APIResponse> updateCart(){
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteDishFromCart(@PathVariable Integer id){
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
}
