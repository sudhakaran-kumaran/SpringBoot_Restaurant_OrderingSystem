package com.restapi.service;

import com.restapi.dto.OrderDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.*;
import com.restapi.repository.*;
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

    @Transactional
    public List<OrderResponse> placeOrder(Long userId){
        AppUser appUser = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("userId","userId",userId));
        OrderStatus orderStatus =   orderStatusRepository.findById(1L).orElseThrow(()->new ResourceNotFoundException("orderStatusId","orderStatusId",1));
        List<Cart> cartList= cartRepository.findUserCart(userId).orElseThrow(()-> new ResourceNotFoundException("userId","userId",userId));
        Order order = new Order();
        order.setOrderStatus(orderStatus);
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
        return findUserOrders(userId);
    }

    public List<OrderResponse> findUserOrders(Long userId) {
        List<Order> orderList=orderRepository.findUserOrder(userId).orElseThrow(()-> new ResourceNotFoundException("userId","userId",userId));
        return orderDto.mapToOrderResponse(orderList);
    }
}
