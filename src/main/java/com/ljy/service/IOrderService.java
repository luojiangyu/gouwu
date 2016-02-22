package com.ljy.service;

import java.util.List;

import com.ljy.vo.Order;

public interface IOrderService {
	public int insertBatchOrder(List<Order> order);
}
