package com.altapay.backend.services;

import com.altapay.backend.exceptions.MerchantApiServiceException;
import com.altapay.backend.model.ShopOrder;
import org.springframework.beans.factory.annotation.Autowired;

public class ShopOrderService
{
    private InventoryService inventoryService;

    private MerchantApiService merchantApiService;

    @Autowired
    public ShopOrderService(
        InventoryService inventoryService, MerchantApiService merchantApiService )
    {
        this.inventoryService = inventoryService;
        this.merchantApiService = merchantApiService;
    }

    public void capture( ShopOrder shopOrder )
    {
        shopOrder.getOrderLines().forEach( orderLine ->
        {
            if ( inventoryService
                .checkInventory( orderLine.getProduct(), orderLine.getQuantity() ) )
            {
                try
                {
                    if ( merchantApiService.capturePayment( shopOrder ).wasSuccessful() )
                    {
                        inventoryService
                            .takeFromInventory( orderLine.getProduct(), orderLine.getQuantity() );
                    }
                } catch ( MerchantApiServiceException e )
                {
                    System.err.println( String
                        .format( "An error occured during capturing payment: %s",
                            e.getMessage() ) );
                }
            }
        } );
    }

    public void release( ShopOrder shopOrder )
    {
        try
        {
            merchantApiService.releasePayment( shopOrder );
        } catch ( MerchantApiServiceException e )
        {
            System.err.println( String
                .format( "An error occured during releasing payment: %s", e.getMessage() ) );
        }
    }
}
