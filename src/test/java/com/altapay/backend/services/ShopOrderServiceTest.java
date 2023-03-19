package com.altapay.backend.services;

import com.altapay.backend.exceptions.MerchantApiServiceException;
import com.altapay.backend.model.OrderLine;
import com.altapay.backend.model.Product;
import com.altapay.backend.model.ShopOrder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

public class ShopOrderServiceTest
{
    private static final String PRODUCT_NAME = "Product A";

    private static final String PRODUCT_ID = "1";

    private static final String ID = "orderid";

    private static final String PAYMENTID = "paymentid";

    private OrderLine orderLine;

    private ShopOrder order;

    private Product product;

    @Mock
    private InventoryService inventoryService;

    @Mock
    private MerchantApiService merchantApiService;

    private ShopOrderService shopOrderService;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks( this );

        product = Product.builder()
            .name( PRODUCT_NAME )
            .id( PRODUCT_ID )
            .build();

        orderLine = OrderLine.builder()
            .product( product )
            .quantity( 10 )
            .build();

        List<OrderLine> orderLines = List.of( orderLine );

        order = ShopOrder.builder()
            .orderLines( orderLines )
            .paymentId( PAYMENTID )
            .id( ID )
            .build();

        shopOrderService =
            new ShopOrderService( inventoryService, merchantApiService );
    }

    @Test
    public void executeCapture_inventoryIsNotChecked() throws Exception
    {
        when( inventoryService.checkInventory( product, orderLine.getQuantity() ) )
            .thenReturn( false );

        shopOrderService.capture( order );

        verify( inventoryService ).checkInventory( product, orderLine.getQuantity() );
        verify( merchantApiService, never() ).capturePayment( order );
        verify( inventoryService, never() ).takeFromInventory( product, orderLine.getQuantity() );
    }

    @Test
    public void executeCapture_inventoryIsChecked_paymentNotCaptured() throws Exception
    {
        final var captureResponse = mock( CaptureResponse.class );

        when( inventoryService.checkInventory( product, orderLine.getQuantity() ) )
            .thenReturn( true );
        when( merchantApiService.capturePayment( order ) ).thenReturn( captureResponse );
        when( captureResponse.wasSuccessful() ).thenReturn( false );

        shopOrderService.capture( order );

        verify( inventoryService ).checkInventory( product, orderLine.getQuantity() );
        verify( merchantApiService ).capturePayment( order );
        verify( inventoryService, never() ).takeFromInventory( product, orderLine.getQuantity() );
    }

    @Test
    public void executeCapture_inventoryIsChecked_paymentCaptureThrowsAnException() throws Exception
    {
        when( inventoryService.checkInventory( product, orderLine.getQuantity() ) )
            .thenReturn( true );
        when( merchantApiService.capturePayment( order ) )
            .thenThrow( new MerchantApiServiceException() );

        shopOrderService.capture( order );

        verify( inventoryService ).checkInventory( product, orderLine.getQuantity() );
        verify( merchantApiService ).capturePayment( order );
        verify( inventoryService, never() ).takeFromInventory( product, orderLine.getQuantity() );
    }

    @Test
    public void executeCapture_inventoryIsChecked_paymentIsCaptured()
        throws MerchantApiServiceException
    {
        final var captureResponse = mock( CaptureResponse.class );

        when( inventoryService.checkInventory( product, orderLine.getQuantity() ) )
            .thenReturn( true );
        when( merchantApiService.capturePayment( order ) ).thenReturn( captureResponse );
        when( captureResponse.wasSuccessful() ).thenReturn( true );

        shopOrderService.capture( order );

        verify( inventoryService ).checkInventory( product, orderLine.getQuantity() );
        verify( merchantApiService ).capturePayment( order );
        verify( inventoryService ).takeFromInventory( product, orderLine.getQuantity() );
    }

    @Test
    public void executeRelease_paymentIsReleasedThroughApiService()
        throws MerchantApiServiceException
    {
        shopOrderService.release( order );

        verify( merchantApiService ).releasePayment( order );
    }

}
