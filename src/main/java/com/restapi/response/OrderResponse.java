package com.restapi.response;

import com.restapi.model.OrderedDish;
import com.restapi.model.TableDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Long id;
    private List<OrderedDish> dishList;
    private Long userId;
    private String name;
    private String username;
    private TableDetails tableDetails;
    private String orderStatus;

}
