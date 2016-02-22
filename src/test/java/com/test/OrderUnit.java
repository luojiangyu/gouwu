package com.test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.ljy.service.IOrderService;
import com.ljy.vo.Order;


public class OrderUnit {

	private IOrderService orderService=null;
	@Before
	public void init() {
		@SuppressWarnings("resource")
		ApplicationContext aCtx = new FileSystemXmlApplicationContext("classpath:conf/applicationContext.xml");
		IOrderService service = aCtx.getBean(IOrderService.class);
		Assert.assertNotNull(service);
		this.orderService = service;
	}
	@Test
	public void insertBatchOrder(){
		List<Order> orderList=new ArrayList<Order>();
		for(int i=0;i<100;i++){
			Order order=new Order();
			order.setOrderId(UUID.randomUUID().toString());
			order.setGoodsId(1);
			order.setPrice(100.0f);
			order.setPromotionPrice(80.1f);
			order.setUserId(i);
			orderList.add(order);
			
		}
		System.out.println(orderList.size());
		orderService.insertBatchOrder(orderList);
	}
}
