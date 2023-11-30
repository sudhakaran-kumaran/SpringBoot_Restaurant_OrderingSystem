package com.restapi.response;

import com.restapi.model.TableDetails;
import com.restapi.request.TableRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TableResponse {
    private List<TableDetails> tableList;
}
