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
    @Autowired
    private CategoryRepository categoryRepository;

    public List<DishResponse> mapToDishResponse(List<Dish> dishList){


        ArrayList<DishResponse> dishResponses = new ArrayList<>();
        for(Dish dish:dishList)
        {
            DishResponse dishResponse = new DishResponse();
            dishResponse.setId(dish.getId());
            dishResponse.setTitle(dish.getTitle());
            dishResponse.setPrice(dish.getPrice());
            dishResponse.setDescription(dish.getDescription());
            dishResponse.setPhoto(dish.getPhoto());
            dishResponse.setCategoryId(dish.getCategory().getId());

            dishResponses.add(dishResponse);
        }

        return dishResponses;
    }


    public Dish mapToDish(DishRequest dishRequest) {
        Dish dish = new Dish();
        if(dishRequest.getId() != null){
            dish.setId(dishRequest.getId());
        }
        System.out.println(dishRequest.getCategoryId());
        System.out.println(dishRequest.getPrice());
        Category category = categoryRepository.findById(dishRequest.getCategoryId())
                .orElseThrow(()-> new ResourceNotFoundException("CategoryId","CategoryId",dishRequest.getCategoryId()));
        System.out.println(category);
        dish.setPrice(dishRequest.getPrice());
        dish.setDescription(dishRequest.getDescription());
        dish.setTitle(dishRequest.getTitle());
        dish.setPhoto(dishRequest.getPhoto());
        dish.setCategory(category);
        return dish;
    }
}
