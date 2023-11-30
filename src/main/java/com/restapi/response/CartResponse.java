package com.restapi.response;

import com.restapi.request.CartRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class CartResponse {
    List<CartRequest> cartRequests=new ArrayList<>();
}
