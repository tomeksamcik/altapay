package com.altapay.backend.ioc;

import com.altapay.backend.model.*;

public class BackendContainer implements ModelFactory
{

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
