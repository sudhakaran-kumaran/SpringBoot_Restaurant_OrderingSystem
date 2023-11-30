package com.restapi.controller.admin;

import com.restapi.model.OrderStatus;
import com.restapi.model.Role;
import com.restapi.request.OrderStatusRequest;
import com.restapi.response.OrderResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.CartService;
import com.restapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/admin/order")
@RolesAllowed(Role.ADMIN)
public class AdminOrderController {
    @Autowired
    private APIResponse apiResponse;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllOrders() {
        List<OrderResponse> orderResponse=orderService.findAllOrders();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(orderResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<APIResponse> getUsersOrder(@PathVariable Long userId) {
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(orderService.findUserOrders(userId));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @GetMapping("/status/all")
    public ResponseEntity<APIResponse> getAllStatus(){
        List<OrderStatus> orderStatusList=orderService.findAllOrderStatus();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(orderStatusList);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }


    @PutMapping("/updatestatus")
    public ResponseEntity<APIResponse> updateOrderStatus(@RequestBody OrderStatusRequest orderStatusRequest) {
        List<OrderResponse> orderResponseList = orderService.updateOrderStatus(orderStatusRequest.getOrderId(),orderStatusRequest.getStatusId());
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(orderResponseList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
