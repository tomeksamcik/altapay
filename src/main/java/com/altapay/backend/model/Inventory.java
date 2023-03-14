package com.altapay.backend.model;

import lombok.Data;

@Data
public class Inventory 
{
	private Product product;

	private int count;
}
