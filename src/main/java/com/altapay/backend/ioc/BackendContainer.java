package com.altapay.backend.ioc;

import com.altapay.backend.controllers.BackendController;
import com.altapay.backend.model.*;
import com.altapay.backend.repositories.InventoryRepository;
import com.altapay.backend.repositories.ShopOrderRepositoryFactory;
import com.altapay.backend.services.InventoryService;
import com.altapay.backend.services.MerchantApiService;
import com.altapay.util.HttpUtil;
import com.altapay.util.XpathUtil;

public class BackendContainer implements IModelFactory
{
    private final InventoryService inventoryService =
        new InventoryService( new InventoryRepository() );

    private final MerchantApiService merchantApiService =
        new MerchantApiService( new HttpUtil(), new XpathUtil() );

    public BackendController getBackendController()
    {
        return new BackendController( ShopOrderRepositoryFactory.getInstance( this ) );
    }

    @Override
    public ShopOrder getShopOrder()
    {
        return ShopOrder.builder().inventoryService( inventoryService )
            .merchantApiService( merchantApiService ).build();
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
