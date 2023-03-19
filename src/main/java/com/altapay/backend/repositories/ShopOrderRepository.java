package com.altapay.backend.repositories;

import com.altapay.backend.model.OrderLine;
import com.altapay.backend.model.Product;
import com.altapay.backend.model.ShopOrder;

import java.util.List;

public class ShopOrderRepository
{

    public ShopOrder loadShopOrder( String shopOrderId )
    {
        return ShopOrder.builder()
            .orderLines( List.of(
                getOrderLine( "1", "Keyboard", 1 ) ) )
            .id( shopOrderId )
            .build();
    }

    private OrderLine getOrderLine( String productId, String name, int quantity )
    {
        return OrderLine.builder()
            .product( Product.builder()
                .id( productId )
                .name( name )
                .build() )
            .quantity( quantity )
            .build();
    }

    public void saveShopOrder( ShopOrder shopOrder )
    {
        System.out.println( "Hurray, you saved the shopOrder: " + shopOrder );
    }

}
