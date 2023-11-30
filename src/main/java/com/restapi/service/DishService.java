package com.restapi.service;

import com.restapi.dto.DishDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.Category;
import com.restapi.model.Dish;
import com.restapi.repository.CategoryRepository;
import com.restapi.repository.DishRepository;
import com.restapi.request.DishRequest;
import com.restapi.response.DishResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class DishService {
    @Autowired
    private DishDto dishDto;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private StorageService storageService;


    public List<DishResponse> findAll(){
        return dishDto.mapToDishResponse(dishRepository.findAll());
    }
    @Transactional
    public List<DishResponse> createDish(DishRequest dishRequest){
        Dish dish=dishDto.mapToDish(dishRequest);
        Category category = categoryRepository.findById(dishRequest.getCategoryId()).orElseThrow(()-> new ResourceNotFoundException("CategoryId","CategoryId",dishRequest.getCategoryId()));
        dish.setCategory(category);
        dishRepository.save(dish);
        return findAll();
    }
    @Transactional
    public List<DishResponse>  updateDish(DishRequest dishRequest){

        Dish dish=dishDto.mapToDish(dishRequest);
        Category category = categoryRepository.findById(dishRequest.getCategoryId()).orElseThrow(()-> new ResourceNotFoundException("CategoryId","CategoryId",dishRequest.getCategoryId()));
        dish.setCategory(category);
        dishRepository.save(dish);
        return findAll();
    }
    public List<DishResponse> deleteById(Integer id){
        dishRepository.deleteById(Long.valueOf(id));
        return findAll();
    }

    public Dish findByCategoryId(Long categoryId) {
        return dishRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("user","id",categoryId));
    }

    public File getFile(Long id) throws IOException {
        Dish dish = dishRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id", "id", id));

        Resource resource = storageService.loadFileAsResource(dish.getPhoto());

        return resource.getFile();
    }
}
