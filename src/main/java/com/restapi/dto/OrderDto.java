package com.restapi.dto;

import com.restapi.model.Order;
import com.restapi.response.OrderResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDto {

    public List<OrderResponse> mapToOrderResponse(List<Order> orderList) {
        List<OrderResponse> orderResponseList=new ArrayList<>();
        for (Order order:orderList){
            OrderResponse orderResponse=new OrderResponse();
            orderResponse.setId(order.getId());
            orderResponse.setUserId(order.getAppUser().getId());
            orderResponse.setName(order.getAppUser().getName());
            orderResponse.setOrderStatus(order.getOrderStatus().getStatus());
            orderResponse.setTableDetails(order.getTableDetails());
            orderResponse.setDishList(order.getOrderedDishList());
            orderResponseList.add(orderResponse);
        }
        return orderResponseList;
    }
}
