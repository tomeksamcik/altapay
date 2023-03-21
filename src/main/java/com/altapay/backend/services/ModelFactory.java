package com.altapay.backend.services;

import com.altapay.backend.model.Inventory;
import com.altapay.backend.model.OrderLine;
import com.altapay.backend.model.Product;
import com.altapay.backend.model.ShopOrder;

public interface ModelFactory
{
    OrderLine createOrderLine( String productId, String name, int quantity );

    ShopOrder createShopOrder( String shopOrderId );

    Inventory createInventory();

    Product createProduct();
}
