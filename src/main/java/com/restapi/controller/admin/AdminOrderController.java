package com.restapi.controller.admin;

import com.restapi.response.common.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/order")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminOrderController {

    @Autowired
    private APIResponse apiResponse;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllOrders() {

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<APIResponse> getUsersOrder(@PathVariable Integer userId) {

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<APIResponse> updateOrderStatus() {

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }




}
