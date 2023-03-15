package com.altapay.backend.model;

import com.altapay.backend.exceptions.MerchantApiServiceException;
import com.altapay.backend.services.InventoryService;
import com.altapay.backend.services.MerchantApiService;
import lombok.Builder;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;


@Setter
@Builder
public class ShopOrder 
{
	private String id;

	private String paymentId;

	private List<OrderLine> orderLines;

	@NonNull
	private InventoryService inventoryService;

	@NonNull
	private MerchantApiService merchantApiService;

	public void capture()
	{
		orderLines.forEach( orderLine ->
		{
			if ( inventoryService
				.checkInventory( orderLine.getProduct(), orderLine.getQuantity() ) )
			{
				try
				{
					if ( merchantApiService.capturePayment( this ).wasSuccessful() )
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

	// Release is a synonym for canceling a payment
	public void release() 
	{
		try
		{
			merchantApiService.releasePayment( this );
		} catch ( MerchantApiServiceException e )
		{
			System.err.println( String
				.format( "An error occured during releasing payment: %s", e.getMessage() ) );
		}
	}
}
