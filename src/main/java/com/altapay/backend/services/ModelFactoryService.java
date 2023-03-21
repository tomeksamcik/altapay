package com.altapay.backend.services;

import com.altapay.backend.model.Inventory;
import com.altapay.backend.model.OrderLine;
import com.altapay.backend.model.Product;
import com.altapay.backend.model.ShopOrder;

import java.util.List;

public class ModelFactoryService implements ModelFactory
{
    @Override
    public OrderLine createOrderLine( String productId, String name, int quantity )
    {
        return OrderLine.builder()
            .product( Product.builder()
                .id( productId )
                .name( name )
                .build() )
            .quantity( quantity )
            .build();
    }

    @Override
    public ShopOrder createShopOrder( String shopOrderId )
    {
        return ShopOrder.builder()
            .orderLines( List.of(
                createOrderLine( "1", "Keyboard", 1 ) ) )
            .id( shopOrderId )
            .build();
    }

    @Override
    public Inventory createInventory()
    {
        return null;
    }

    @Override
    public Product createProduct()
    {
        return null;
    }
}
