package com.restapi.dto;

import com.restapi.model.AppUser;
import com.restapi.model.Cart;
import com.restapi.model.Dish;
import com.restapi.request.CartRequest;
import com.restapi.request.DishRequest;
import com.restapi.request.RegisterRequest;
import com.restapi.response.AuthResponse;
import com.restapi.response.CartResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartDto {


    public CartResponse mapToCart(List<Cart> cart) {
        CartResponse cartResponse = new CartResponse();
        ArrayList<CartRequest> cartRequests = new ArrayList<>();
        for(Cart cartItem:cart)
        {
            CartRequest cartRequest = new CartRequest();
            cartRequest.setDishId(cartItem.getDish().getId());
            cartRequest.setUserId(cartItem.getAppUser().getId());
            cartRequest.setCount(cartItem.getCount());

            cartRequests.add(cartRequest);
        }
        cartResponse.setCartRequests(cartRequests);
        return cartResponse;
    }
}
