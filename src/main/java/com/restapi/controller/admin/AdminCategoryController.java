package com.restapi.controller.admin;

import com.restapi.request.CategoryRequest;
import com.restapi.response.CategoryResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("/api/admin/category")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminCategoryController {
    @Autowired
    private APIResponse apiResponse;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllCategories(){
        CategoryResponse categoryResponse=categoryService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(categoryResponse.getCategories());
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<APIResponse> createCategory(@RequestBody CategoryRequest categoryRequest){
        CategoryResponse categoryResponse=categoryService.create(categoryRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(categoryResponse.getCategories());
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<APIResponse> updateCategory(@RequestBody
                                                      CategoryRequest categoryRequest) {
        CategoryResponse categoryResponse = categoryService.update(categoryRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(categoryResponse.getCategories());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteCategory(@PathVariable Integer id) {
        CategoryResponse categoryResponse = categoryService.deleteById(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(categoryResponse.getCategories());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
