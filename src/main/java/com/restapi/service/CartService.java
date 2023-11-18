package com.restapi.service;

import com.restapi.dto.CartDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.AppUser;
import com.restapi.model.Cart;
import com.restapi.model.Dish;
import com.restapi.repository.CartRepository;
import com.restapi.repository.CategoryRepository;
import com.restapi.repository.DishRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.CartRequest;
import com.restapi.response.CartResponse;
import com.restapi.response.DishResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private CartDto cartDto;

    public CartResponse findUserCart(Long userId){
        List<Cart> cart = cartRepository.findUserCart(userId).orElseThrow(()-> new ResourceNotFoundException("cart","userId",userId));
        return cartDto.mapToCart(cart);
    }
    @Transactional
    public CartResponse addToCart(CartRequest cartRequest){
        AppUser appUser= userRepository.findById(cartRequest.getUserId()).orElseThrow(()-> new ResourceNotFoundException("userId","userId",cartRequest.getUserId()));
        Dish dish = dishRepository.findById(cartRequest.getDishId())
                .orElseThrow(() -> new ResourceNotFoundException("dishId", "dishId",
                        cartRequest.getDishId()));
        Optional<List<Cart>> cartOptional = cartRepository.findUserCart(cartRequest.getUserId());
        if(cartOptional.isPresent()){
            boolean isPresent = false;
            for(Cart cart:cartOptional.get()){
                if(cart.getDish().getId().equals(cartRequest.getDishId())){
                    cart.setCount(cartRequest.getCount());
                    cartRepository.save(cart);
                    isPresent=true;
                }
            }
            if(!isPresent){
                Cart cart=new Cart();
                cart.setAppUser(appUser);
                cart.setDish(dish);
                cart.setCount(cartRequest.getCount());
                cartRepository.save(cart);
            }
        }else {
            Cart cart = new Cart();
            cart.setAppUser(appUser);
            cart.setDish(dish);
            cart.setCount(cartRequest.getCount());
            cartRepository.save(cart);

        }
        return findUserCart(cartRequest.getUserId());

    }
    public CartResponse deleteUserCart(Integer id, Integer userId){
        dishRepository.deleteById(Long.valueOf(id));
        return findUserCart(Long.valueOf(userId));
    }


}
