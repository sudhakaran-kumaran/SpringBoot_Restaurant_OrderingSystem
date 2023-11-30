package com.restapi.service;

import com.restapi.dto.OrderDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.*;
import com.restapi.repository.*;
import com.restapi.request.OrderRequest;
import com.restapi.response.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderDto orderDto;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderStatusRepository orderStatusRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderedDishRepository orderedDishRepository;
    @Autowired
    private TableRepository tableRepository;

    @Transactional
    public List<OrderResponse> placeOrder(OrderRequest orderRequest){
        AppUser appUser = userRepository.findById(orderRequest.getUserId()).orElseThrow(()->new ResourceNotFoundException("userId","userId",orderRequest.getUserId()));
        TableDetails tableDetails=tableRepository.findById(orderRequest.getTableId()).orElseThrow(()-> new ResourceNotFoundException("tableId","tableId",orderRequest.getTableId()));
        OrderStatus orderStatus =   orderStatusRepository.findById(1L).orElseThrow(()->new ResourceNotFoundException("orderStatusId","orderStatusId",1));
        List<Cart> cartList= cartRepository.findUserCart(orderRequest.getUserId()).orElseThrow(()-> new ResourceNotFoundException("userId","userId",orderRequest.getDishId()));
        Order order = new Order();
        order.setOrderStatus(orderStatus);
        order.setTableDetails(tableDetails);
        order.setAppUser(appUser);
        order = orderRepository.save(order);

        for(Cart cart:cartList){
            OrderedDish orderedDish =new OrderedDish();
            orderedDish.setOrder(order);
            orderedDish.setTitle(cart.getDish().getTitle());
            orderedDish.setDescription(cart.getDish().getDescription());
            orderedDish.setPrice(cart.getDish().getPrice());
            orderedDish.setCount(cart.getCount());
            orderedDishRepository.save(orderedDish);
        }
        return findUserOrders(orderRequest.getUserId());
    }

    public List<OrderResponse> findUserOrders(Long userId) {
        List<Order> orderList=orderRepository.findUserOrder(userId).orElseThrow(()-> new ResourceNotFoundException("userId","userId",userId));
        return orderDto.mapToOrderResponse(orderList);
    }
    public List<OrderResponse> findAllOrders(){
        List<Order> orderList=orderRepository.findAll();
        return orderDto.mapToOrderResponse(orderList);
    }
    public List<OrderStatus> findAllOrderStatus(){
        return orderStatusRepository.findAll();
    }
    public List<OrderResponse> updateOrderStatus(Long orderId,Long statusId){
        Order order=orderRepository.findById(orderId).orElseThrow(()->new ResourceNotFoundException("orderId","orderId",orderId));
        OrderStatus orderStatus=orderStatusRepository.findById(statusId).orElseThrow(()-> new ResourceNotFoundException("statusId","statusId",statusId));
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
        return findAllOrders();
    }
}
