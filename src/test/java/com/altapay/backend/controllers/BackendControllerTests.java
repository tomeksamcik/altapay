package com.altapay.backend.controllers;

import com.altapay.backend.model.ShopOrder;
import com.altapay.backend.repositories.ShopOrderRepository;
import com.altapay.backend.services.ShopOrderService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BackendControllerTests
{
    private static final String ORDER_ID = "Some order id";

    private BackendController controller;

    @Mock
    private ShopOrderRepository shopOrderRepository;

    @Mock
    private ShopOrderService shopOrderService;

    private ShopOrder shopOrder = ShopOrder.builder().build();

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks( this );

        when( shopOrderRepository.loadShopOrder( ORDER_ID ) ).thenReturn( shopOrder );

        controller = new BackendController( shopOrderRepository, shopOrderService );
    }

    @Test
    public void capturePaymentGetsTheOrderFromTheRepository()
    {
        controller.capturePayment( ORDER_ID );

        verify( shopOrderRepository ).loadShopOrder( ORDER_ID );
    }

    @Test
    public void capturePaymentMustInvokeCaptureOnTheOrder()
    {
        controller.capturePayment( ORDER_ID );

        verify( shopOrderService ).capture( shopOrder );
    }

    @Test
    public void capturePaymentSavesTheOrderItLoaded()
    {
        controller.capturePayment( ORDER_ID );

        verify( shopOrderRepository ).saveShopOrder( shopOrder );
    }

    @Test
    public void releasePaymentGetsTheOrderFromTheRepository()
    {
        controller.releasePayment( ORDER_ID );

        verify( shopOrderRepository ).loadShopOrder( ORDER_ID );
    }

    @Test
    public void releasePaymentMustInvokeReleaseOnTheOrder()
    {
        controller.releasePayment( ORDER_ID );

        verify( shopOrderService ).release( shopOrder );
    }

    @Test
    public void releasePaymentSavesTheOrderItLoaded()
    {
        controller.releasePayment( ORDER_ID );

        verify( shopOrderRepository ).saveShopOrder( shopOrder );
    }

}
