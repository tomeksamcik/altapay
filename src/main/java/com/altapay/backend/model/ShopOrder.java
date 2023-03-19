package com.altapay.backend.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ShopOrder
{
    private String id;

    private String paymentId;

    private List<OrderLine> orderLines;
}
