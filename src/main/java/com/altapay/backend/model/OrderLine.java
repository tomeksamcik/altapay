package com.altapay.backend.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderLine 
{
	private Product product;

	private int quantity;
}
