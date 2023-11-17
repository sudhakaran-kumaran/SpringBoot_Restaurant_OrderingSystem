package com.restapi.service;

import com.restapi.dto.DishDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.Category;
import com.restapi.model.Dish;
import com.restapi.repository.CategoryRepository;
import com.restapi.repository.DishRepository;
import com.restapi.request.DishRequest;
import com.restapi.response.DishResponse;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DishService {
    @Autowired
    private DishDto dishDto;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private DishRepository dishRepository;


    public DishResponse findAll(){
        return dishDto.mapToDishResponse(dishRepository.findAll());
    }
    @Transactional
    public DishResponse createDish(DishRequest dishRequest){
        Dish dish=dishDto.mapToDish(dishRequest);
        Category category = categoryRepository.findById(dishRequest.getCategoryId()).orElseThrow(()-> new ResourceNotFoundException("CategoryId","CategoryId",dishRequest.getCategoryId()));
        dish.setCategory(category);
        dishRepository.save(dish);
        return findAll();
    }
    @Transactional
    public DishResponse updateDish(DishRequest dishRequest){
        Dish dish=dishDto.mapToDish(dishRequest);
        Category category = categoryRepository.findById(dishRequest.getCategoryId()).orElseThrow(()-> new ResourceNotFoundException("CategoryId","CategoryId",dishRequest.getCategoryId()));
        dish.setCategory(category);
        dishRepository.save(dish);
        return findAll();
    }
    public DishResponse deleteById(Integer id){
        dishRepository.deleteById(Long.valueOf(id));
        return findAll();
    }
}
