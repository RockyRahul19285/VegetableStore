package com.cg.service;

import java.util.List;

import com.cg.dto.OrdersDTO;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.OrderNotFoundException;

public interface OrdersService {
	public OrdersDTO addOrders(int custid) throws CustomerNotFoundException;

	public String deleteOrders(int orderId);

	public OrdersDTO getById(int id) throws OrderNotFoundException;

	public List<OrdersDTO> findAll();

	public List<OrdersDTO> getOrderCustomerId(int customerId);
	
	public String orderAccepted(int orderId);
	
	public String orderDelivered(int orderId);
}
