package com.restapi.controller;

import com.restapi.request.CartRequest;
import com.restapi.response.common.APIResponse;
import com.restapi.service.CartService;
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
    @Autowired
    private CartService cartService;
    @GetMapping("/{userId}")
    public ResponseEntity<APIResponse> getUserCart(@PathVariable Long userId){
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(cartService.findUserCart(userId));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @PostMapping("/addtoCart")
    public ResponseEntity<APIResponse> addtoCart(@RequestBody CartRequest cartRequest){
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(cartService.addToCart(cartRequest));
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<APIResponse> updateCart(){
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
    @DeleteMapping("/{userId}/{id}")
    public ResponseEntity<APIResponse> deleteDishFromCart(@PathVariable Integer id,@PathVariable Integer userId){

        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(cartService.deleteUserCart(id,userId));
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
}
