package com.restapi.controller.admin;

import com.restapi.model.Dish;
import com.restapi.model.Role;
import com.restapi.request.DishRequest;
import com.restapi.response.DishResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.DishService;
import com.restapi.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/admin/dish")
@RolesAllowed(Role.ADMIN)
public class AdminDishController {
    @Autowired
    private APIResponse apiResponse;
    @Autowired
    private DishService dishService;
    @Autowired
    private StorageService storageService;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllDishes() {
       List<DishResponse> dishResponse = dishService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(dishResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<APIResponse> createDish(@RequestParam("photo") MultipartFile photo,
                                                  @RequestParam("categoryId") Long categoryId,
                                                  @RequestParam("title") String title,
                                                  @RequestParam("description") String description,
                                                  @RequestParam("price") Double price) throws IOException {
        String file = storageService.storeFile(photo);
        DishRequest dishRequest = new DishRequest();
        dishRequest.setCategoryId(categoryId);
        dishRequest.setTitle(title);
        dishRequest.setPhoto(file);
        dishRequest.setDescription(description);
        dishRequest.setPrice(price);
       List<DishResponse>  dishList = dishService.createDish(dishRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(dishList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<APIResponse> updateDish(@RequestParam("photo") MultipartFile file, @RequestBody DishRequest dishRequest) {
        List<DishResponse>  dishList = dishService.updateDish(dishRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(dishList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<APIResponse> deleteDish(@PathVariable Integer id) {
        List<DishResponse>  dishList = dishService.deleteById(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(dishList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @GetMapping("/downloadFile/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) throws IOException {

        File file = dishService.getFile(id);

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
