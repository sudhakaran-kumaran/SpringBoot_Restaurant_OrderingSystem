package com.restapi.controller.admin;

import com.restapi.model.Dish;
import com.restapi.model.Role;
import com.restapi.request.DishRequest;
import com.restapi.response.DishResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/admin/dish")
@RolesAllowed(Role.ADMIN)
public class AdminDishController {
    @Autowired
    private APIResponse apiResponse;
    @Autowired
    private DishService dishService;
    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllDishes(){
        DishResponse dishResponse =dishService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(dishResponse);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<APIResponse> createDish(@RequestBody DishRequest dishRequest){
        DishResponse dishList = dishService.createDish(dishRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(dishList);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);

    }
    @PutMapping("/update")
    public ResponseEntity<APIResponse> updateDish(@RequestBody DishRequest dishRequest){
        DishResponse dishList=dishService.updateDish(dishRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(dishList);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<APIResponse> deleteDish(@PathVariable Integer id){
        DishResponse dishList=dishService.deleteById(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(dishList);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
}
