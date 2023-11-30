package com.restapi.dto;

import com.restapi.model.Cart;
import com.restapi.request.CartRequest;
import com.restapi.response.CartResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartDto {
    public CartResponse mapToCartResponse(List<Cart> cartList){
        CartResponse cartResponse=new CartResponse();

        ArrayList<CartRequest> cartRequests=new ArrayList<>();
        for(Cart cart:cartList){

            CartRequest cartRequest = new CartRequest();
            cartRequest.setDishId(cart.getDish().getId());
            cartRequest.setPrice(cart.getDish().getPrice());
            cartRequest.setUserId(cart.getAppUser().getId());
            cartRequest.setDishTitle(cart.getDish().getTitle());
            cartRequest.setCount(cart.getCount());
            cartRequests.add(cartRequest);
        }
        cartResponse.setCartRequests(cartRequests);
        return cartResponse;
    }

}
