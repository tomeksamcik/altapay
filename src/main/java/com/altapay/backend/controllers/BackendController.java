package com.altapay.backend.controllers;

import com.altapay.backend.model.ShopOrder;
import com.altapay.backend.repositories.ShopOrderRepository;

public class BackendController {
	
	private ShopOrderRepository shopOrderRepository;

	public BackendController(ShopOrderRepository shopOrderRepository) 
	{
		this.shopOrderRepository = shopOrderRepository;
	}

	public void capturePayment(String shopOrderId) 
	{
		ShopOrder order = shopOrderRepository.loadShopOrder(shopOrderId);
		
		order.capture();

		shopOrderRepository.saveShopOrder( order );
	}

	public void releasePayment(String shopOrderId) 
	{
		ShopOrder order = shopOrderRepository.loadShopOrder( shopOrderId );
		
		order.release();

		shopOrderRepository.saveShopOrder( order );
	}

}
