package com.altapay.backend.repositories;

import com.altapay.backend.model.OrderLine;
import com.altapay.backend.model.ShopOrder;
import com.altapay.backend.services.ModelFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ShopOrderRepository
{
    private final ModelFactory modelFactoryService;

    @Autowired
    public ShopOrderRepository( ModelFactory modelFactoryService )
    {
        this.modelFactoryService = modelFactoryService;
    }

    public ShopOrder loadShopOrder( String shopOrderId )
    {
        return modelFactoryService.createShopOrder( shopOrderId );
    }

    private OrderLine getOrderLine( String productId, String name, int quantity )
    {
        return modelFactoryService.createOrderLine( productId, name, quantity );
    }

    public void saveShopOrder( ShopOrder shopOrder )
    {
        System.out.println( "Hurray, you saved the shopOrder: " + shopOrder );
    }

}
