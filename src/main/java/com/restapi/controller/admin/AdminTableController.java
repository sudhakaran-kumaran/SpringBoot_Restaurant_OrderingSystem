package com.restapi.controller.admin;

import com.restapi.model.AppUser;
import com.restapi.model.Role;
import com.restapi.request.TableRequest;
import com.restapi.response.TableResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.TableService;
import com.restapi.service.UserService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/admin")
@RolesAllowed(Role.ADMIN)
public class AdminTableController {
    @Autowired
    private APIResponse apiResponse;
    @Autowired
    private UserService userService;
    @Autowired
    private TableService tableService;

    @GetMapping("table/all")
    public ResponseEntity<APIResponse> getAllTable(){
        TableResponse tableResponse=tableService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(tableResponse);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);

    }

    @GetMapping("/table/{userId}")
    public ResponseEntity<APIResponse> getUserDetails(@PathVariable Long userId){
        AppUser appUser=userService.findUserById(userId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(appUser);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
    @PostMapping("/create/table")
    public ResponseEntity<APIResponse> createTable(@RequestBody TableRequest tableRequest){
        TableResponse tableResponse=tableService.create(tableRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(tableResponse.getTableList());
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
    @PutMapping("/update/table")
    public ResponseEntity<APIResponse> updateTable(@RequestBody TableRequest tableRequest){
        TableResponse tableResponse=tableService.update(tableRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(tableResponse.getTableList());
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<APIResponse> deleteTable(@PathVariable Integer id){
        TableResponse tableResponse=tableService.deleteById(Long.valueOf(id));
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(tableResponse.getTableList());
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);

    }

}
