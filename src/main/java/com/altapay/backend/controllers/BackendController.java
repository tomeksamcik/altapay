package com.altapay.backend.controllers;

import com.altapay.backend.model.ShopOrder;
import com.altapay.backend.repositories.ShopOrderRepository;
import com.altapay.backend.services.ShopOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BackendController
{
    private ShopOrderService shopOrderService;

    private ShopOrderRepository shopOrderRepository;

    @Autowired
    public BackendController(
        ShopOrderRepository shopOrderRepository, ShopOrderService shopOrderService )
    {
        this.shopOrderRepository = shopOrderRepository;
        this.shopOrderService = shopOrderService;
    }

    public void capturePayment( String shopOrderId )
    {
        ShopOrder order = shopOrderRepository.loadShopOrder( shopOrderId );

        shopOrderService.capture( order );

        shopOrderRepository.saveShopOrder( order );
    }

    public void releasePayment( String shopOrderId )
    {
        ShopOrder order = shopOrderRepository.loadShopOrder( shopOrderId );

        shopOrderService.release( order );

        shopOrderRepository.saveShopOrder( order );
    }

}
