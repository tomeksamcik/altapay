package com.altapay.backend.repositories;

import com.altapay.backend.ioc.BackendContainer;
import com.altapay.backend.model.OrderLine;
import com.altapay.backend.model.Product;
import com.altapay.backend.model.ShopOrder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ShopOrderRepository
{

    @Autowired
    private BackendContainer backendContainer;

    public ShopOrder loadShopOrder( String shopOrderId )
    {
        ShopOrder order = backendContainer.getShopOrder();
        order.setId( shopOrderId );
        List<OrderLine> orderLines = new ArrayList<>();
        orderLines.add( getOrderLine( "1", "Keyboard", 1 ) );
        order.setOrderLines( orderLines );
        return order;
    }

    private OrderLine getOrderLine( String productId, String name, int quantity )
    {
        OrderLine orderLine = backendContainer.getOrderLine();
        Product product = backendContainer.getProduct();
        product.setId( productId );
        product.setName( name );
        orderLine.setProduct( product );
        orderLine.setQuantity( quantity );
        return orderLine;
    }

    public void saveShopOrder( ShopOrder shopOrder )
    {
        System.out.println( "Hurray, you saved the shopOrder: " + shopOrder );
    }

}
