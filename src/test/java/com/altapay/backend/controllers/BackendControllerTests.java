package com.altapay.backend.controllers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.altapay.backend.model.ShopOrder;
import com.altapay.backend.repositories.ShopOrderRepository;

public class BackendControllerTests {

	private static final String ORDER_ID = "Some order id";

	@Mock
	private ShopOrderRepository shopOrderRepository;

	@Mock
	private ShopOrder shopOrder;

	private BackendController controller;

	@Before
	public void setup() 
	{
		MockitoAnnotations.initMocks(this);
		
		when(shopOrderRepository.loadShopOrder(ORDER_ID)).thenReturn(shopOrder);
		
		controller = new BackendController(shopOrderRepository);
	}
	
	@Test
	public void capturePaymentGetsTheOrderFromTheRepository()
	{
		controller.capturePayment(ORDER_ID);
		
		verify(shopOrderRepository).loadShopOrder(ORDER_ID);
	}

	@Test
	public void capturePaymentMustInvokeCaptureOnTheOrder()
	{
		controller.capturePayment(ORDER_ID);
		
		verify(shopOrder).capture();
	}

	@Test
	public void capturePaymentSavesTheOrderItLoaded()
	{
		controller.capturePayment(ORDER_ID);

		verify(shopOrderRepository).saveShopOrder( shopOrder );
	}

	@Test
	public void releasePaymentGetsTheOrderFromTheRepository()
	{
		controller.releasePayment(ORDER_ID);

		verify(shopOrderRepository).loadShopOrder(ORDER_ID);
	}

	@Test
	public void releasePaymentMustInvokeReleaseOnTheOrder()
	{
		controller.releasePayment(ORDER_ID);

		verify(shopOrder).release();
	}

	@Test
	public void releasePaymentSavesTheOrderItLoaded()
	{
		controller.releasePayment(ORDER_ID);

		verify(shopOrderRepository).saveShopOrder( shopOrder );
	}
}
