package com.altapay.backend.model;

import lombok.Builder;
import lombok.Setter;

import java.util.List;


@Setter
@Builder
public class ShopOrder 
{
	private String id;

	private String paymentId;

	private final List<OrderLine> orderLines;
	
	public void capture()
	{
		// TODO: use the InventoryService to check inventory before capturing
		// TODO: Use the MerchantApiService to capture the payment. 
		// TODO: use the InventoryService to take from inventory after capturing 
	}

	// Release is a synonym for canceling a payment
	public void release() 
	{
		// TODO: Use the MerchantApiService to release the payment. 
	}
}
