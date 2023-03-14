package com.altapay.backend.model;

import lombok.Data;

@Data
public class OrderLine 
{
	private Product product;

	private int quantity;
}
