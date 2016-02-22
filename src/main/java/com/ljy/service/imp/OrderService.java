package com.ljy.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljy.dao.OrderMapper;
import com.ljy.service.IOrderService;
import com.ljy.vo.Order;

@Service
public class OrderService implements IOrderService {
	@Autowired
	private OrderMapper orderMapper;

	public int insertBatchOrder(List<Order> orderList) {
		System.out.println(orderList.size());
		return orderMapper.insertBatchOrder(orderList);
	}
}
