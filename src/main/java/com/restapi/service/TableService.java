package com.restapi.service;


import com.restapi.dto.TableDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.AppUser;
import com.restapi.model.TableDetails;
import com.restapi.repository.TableRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.TableRequest;
import com.restapi.response.TableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TableService {
    @Autowired
    private TableRepository tableRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TableDto tableDto;
    public TableResponse findAll(){
        return tableDto.maptoTableResponse(tableRepository.findAll());
    }
    @Transactional
    public TableResponse create(TableRequest tableRequest){
        TableDetails tableDetails = tableDto.maptoTable(tableRequest);
        AppUser appUser = userRepository.findById(tableRequest.getUserId()).orElseThrow(()-> new ResourceNotFoundException("userId","userId",tableRequest.getUserId()));
        tableDetails.setAppUser(appUser);
        tableRepository.save(tableDetails);
        return findAll();
    }

    public TableResponse update(TableRequest tableRequest) {
        TableDetails tableDetails = tableDto.maptoTable(tableRequest);
        AppUser appUser = userRepository.findById(tableRequest.getUserId()).orElseThrow(()-> new ResourceNotFoundException("userId","userId",tableRequest.getUserId()));
        tableDetails.setAppUser(appUser);
        tableRepository.save(tableDetails);
        return findAll();
    }

    public TableResponse deleteById(Long id) {
        tableRepository.deleteById(id);
        return findAll();
    }
}
