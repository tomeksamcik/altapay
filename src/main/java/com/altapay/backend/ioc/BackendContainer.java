package com.altapay.backend.ioc;

import com.altapay.backend.controllers.BackendController;
import com.altapay.backend.model.*;
import com.altapay.backend.repositories.ShopOrderRepositoryFactory;

public class BackendContainer implements IModelFactory {

	public BackendController getBackendController() 
	{
		return new BackendController(ShopOrderRepositoryFactory.getInstance( this ));
	}

	@Override
	public ShopOrder getShopOrder() 
	{
		return ShopOrder.builder().build();
	}

	@Override
	public Inventory getInventory() 
	{
		return Inventory.builder().product( Product.builder().build() ).build();
	}

	@Override
	public OrderLine getOrderLine() 
	{
		return OrderLine.builder().build();
	}

	@Override
	public Product getProduct() 
	{
		return Product.builder().build();
	}

}
