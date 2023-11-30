package com.restapi.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderStatusRequest {
    private Long orderId;
    private Long statusId;
    
}
