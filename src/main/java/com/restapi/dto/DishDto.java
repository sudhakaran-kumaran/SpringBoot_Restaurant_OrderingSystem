package com.restapi.dto;

import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.Category;
import com.restapi.model.Dish;
import com.restapi.repository.CategoryRepository;
import com.restapi.request.CategoryRequest;
import com.restapi.request.DishRequest;
import com.restapi.response.CategoryResponse;
import com.restapi.response.DishResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DishDto {

    public DishResponse mapToDishResponse(List<Dish> dishList){

        DishResponse dishResponse = new DishResponse();
        ArrayList<DishRequest> dishRequests = new ArrayList<>();
        for(Dish dish:dishList)
        {
            DishRequest dishRequest = new DishRequest();
            dishRequest.setId(dish.getId());
            dishRequest.setTitle(dish.getTitle());
            dishRequest.setPrice(dish.getPrice());
            dishRequest.setDescription(dish.getDescription());
            dishRequest.setPhoto(dish.getPhoto());
            dishRequest.setCategoryId(dish.getCategory().getId());

            dishRequests.add(dishRequest);
        }
        dishResponse.setDishRequest(dishRequests);
        return dishResponse;
    }


    public Dish mapToDish(DishRequest dishRequest) {
        Dish dish = new Dish();
        if(dishRequest.getId() != null){
            dish.setId(dishRequest.getId());
        }
        dish.setPrice(dishRequest.getPrice());
        dish.setDescription(dishRequest.getDescription());
        dish.setTitle(dishRequest.getTitle());
        dish.setPhoto(dishRequest.getPhoto());
        return dish;
    }

}
