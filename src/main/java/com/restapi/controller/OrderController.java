package com.restapi.controller;

import com.restapi.response.common.APIResponse;
import com.restapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@PreAuthorize("hasRole('ROLE_USER')")
public class OrderController {
    @Autowired
    private APIResponse apiResponse;
    @Autowired
    private OrderService orderService;
    @GetMapping("/{userId}")
    public ResponseEntity<APIResponse> getUserOrders(@PathVariable Long userId){
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(orderService.findUserOrders(userId));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<APIResponse> placeOrders(@PathVariable Long userId){
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(orderService.placeOrder(userId));
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
}
