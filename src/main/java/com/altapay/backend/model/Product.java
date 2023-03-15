package com.altapay.backend.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product
{
    private String id;

    private String name;
}
