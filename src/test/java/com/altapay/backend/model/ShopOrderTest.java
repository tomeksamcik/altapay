package com.altapay.backend.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

public class ShopOrderTest {

	private static final String ID = "orderid";
	private static final String PAYMENTID = "paymentid";
	private ShopOrder order;

	@Before
	public void setUp()
	{
		order = ShopOrder.builder().build();
		order.setId(ID);
		order.setPaymentId(PAYMENTID);
	}

	@Test
	public void executeCapture_inventoryIsChecked() 
	{
		// TODO: Implement test
		fail("Not yet implemented");
	}

	@Test
	public void executeCapture_paymentIsCapturedThroughApiService() 
	{
		// TODO: Implement test
		fail("Not yet implemented");
	}
		
	@Test
	public void executeRelease_paymentIsReleasedThroughApiService() 
	{
		// TODO: Implement test
		fail("Not yet implemented");
	}
	
	// TODO: Add more tests you think is relevant
}
