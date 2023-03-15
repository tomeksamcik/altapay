package com.altapay.backend.model;

import com.altapay.backend.exceptions.MerchantApiServiceException;
import com.altapay.backend.services.CaptureResponse;
import com.altapay.backend.services.InventoryService;
import com.altapay.backend.services.MerchantApiService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

public class ShopOrderTest {

    private static final String PRODUCT_NAME = "Product A";
    private static final String PRODUCT_ID = "1";
    private OrderLine orderLine;
    private Product product;

	private static final String ID = "orderid";
	private static final String PAYMENTID = "paymentid";
	private ShopOrder order;

    @Mock
    private InventoryService inventoryService;

    @Mock
    private MerchantApiService merchantApiService;

    @Before
	public void setUp()
	{
        MockitoAnnotations.initMocks(this);

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
            .merchantApiService( merchantApiService )
            .inventoryService( inventoryService )
            .orderLines( orderLines )
            .paymentId( PAYMENTID )
            .id( ID )
            .build();
	}

    @Test
    public void executeCapture_inventoryIsNotChecked() throws Exception
    {
        when( inventoryService.checkInventory( product, orderLine.getQuantity() ) )
            .thenReturn( false );

        order.capture();

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

        order.capture();

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

        order.capture();

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

        order.capture();

        verify( inventoryService ).checkInventory( product, orderLine.getQuantity() );
        verify( merchantApiService ).capturePayment( order );
        verify( inventoryService ).takeFromInventory( product, orderLine.getQuantity() );
    }
		
	@Test
	public void executeRelease_paymentIsReleasedThroughApiService()
        throws MerchantApiServiceException
	{
        order.release();

        verify( merchantApiService ).releasePayment( order );
	}

}
