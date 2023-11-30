package com.restapi.controller;

import com.restapi.model.Cart;
import com.restapi.model.Role;
import com.restapi.request.CartRequest;
import com.restapi.response.CartResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.response.common.EmptyResponse;
import com.restapi.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RolesAllowed(Role.USER)
public class CartController {
    @Autowired
    private APIResponse apiResponse;
    @Autowired
    private CartService cartService;
    @GetMapping("/{userId}")
    public ResponseEntity<APIResponse> getUserCart(@PathVariable Long userId){
        CartResponse cart=cartService.findUserCart(userId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(cart);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<APIResponse> addtoCart(@RequestBody CartRequest cartRequest){
        CartResponse cart=cartService.addToCart(cartRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(cart);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @DeleteMapping("/{user}/{id}")
    public ResponseEntity<APIResponse> deleteDishFromCart(@PathVariable Integer id,@PathVariable Integer user){
        CartResponse cart=cartService.deleteUserCart(id,user);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(cart);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
}
