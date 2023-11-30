package com.restapi.controller;

import com.restapi.model.Role;
import com.restapi.request.OrderRequest;
import com.restapi.response.OrderResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/order")
@RolesAllowed(Role.USER)
public class OrderController {
    @Autowired
    private APIResponse apiResponse;
    @Autowired
    private OrderService orderService;
    @GetMapping("/{userId}")
    public ResponseEntity<APIResponse> getUserOrders(@PathVariable Long userId){
        List<OrderResponse> orderResponseList=orderService.findUserOrders(userId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(orderResponseList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<APIResponse> placeOrders(@RequestBody OrderRequest orderRequest){
        List<OrderResponse> orderResponseList=orderService.placeOrder(orderRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(orderResponseList);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
}
