package com.altapay.backend.ioc;

import com.altapay.backend.model.Inventory;
import com.altapay.backend.model.OrderLine;
import com.altapay.backend.model.Product;
import com.altapay.backend.model.ShopOrder;

public interface ModelFactory
{
    ShopOrder getShopOrder();

    Inventory getInventory();

    OrderLine getOrderLine();

    Product getProduct();
}
