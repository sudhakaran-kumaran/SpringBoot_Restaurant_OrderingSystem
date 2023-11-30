package com.restapi.dto;

import com.restapi.model.TableDetails;
import com.restapi.request.TableRequest;
import com.restapi.response.TableResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TableDto {
    public TableResponse maptoTableResponse(List<TableDetails> tableDetailsList){
       return new TableResponse(tableDetailsList);
    }
    public TableDetails maptoTable(TableRequest tableRequest){
        TableDetails tableDetails = new TableDetails();
        if(tableRequest.getId() != null){
            tableDetails.setId(tableRequest.getId());
        }

        return tableDetails;
    }
}
