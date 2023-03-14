package com.altapay.backend.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Inventory 
{
	private Product product;

	private int count;
}
