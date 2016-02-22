package com.ljy.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ljy.vo.Order;

@Repository
public interface OrderMapper {
	public int insertBatchOrder(List<Order> order);
}
