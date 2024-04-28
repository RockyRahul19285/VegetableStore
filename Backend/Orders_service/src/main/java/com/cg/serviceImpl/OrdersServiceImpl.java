package com.cg.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.client.CartService;
import com.cg.dto.CartDTO;
import com.cg.dto.OrdersDTO;
import com.cg.entity.Orders;
import com.cg.entity.Status;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.OrderNotFoundException;
import com.cg.repository.OrdersRepository;
import com.cg.service.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService {
	@Autowired
	private CartService cartService;
	@Autowired
	private OrdersRepository ordersRepository;
//	@Autowired
//	private DeliveryPartnerService deliveryPartnerService;


	@Override
	public OrdersDTO addOrders(int customerId) throws CustomerNotFoundException {
		
		
		CartDTO cart=cartService.getCartByCustomerId(customerId);
		if(cart==null) {
			throw new CustomerNotFoundException("Invalid customer Id");
		}
		Orders order= new Orders();
		order.setCustomerId(customerId);
		order.setDate(LocalDateTime.now());
		order.setStatus(Status.pending);
		order.setCartId(cart.getId());
		order.setTotalPrice(cart.getTotalPrice());
		order.setTotalQuantity(cart.getTotalQuantity());
		order.setCartItems(cart.getProductIds());
//		order.setDeliveryPartnerId(0);
		
		ordersRepository.save(order);
		
		cartService.deleteCart(cart.getId());

		return mapOrdersToDTO(order);
	}

	@Override
	public OrdersDTO getById(int id) throws OrderNotFoundException {

		Orders order = ordersRepository.findById(id).get();
		if(order==null) {
			throw new OrderNotFoundException("Invalid order ID");
		}

		return mapOrdersToDTO(order);
	}

	@Override
	public List<OrdersDTO> findAll() {

		List<Orders> orders = ordersRepository.findAll();

		List<OrdersDTO> ordersDTOs = new ArrayList<OrdersDTO>();
		for (Orders order : orders) {
			ordersDTOs.add(mapOrdersToDTO(order));
		}

		return ordersDTOs;
	}

	@Override
	public String deleteOrders(int orderId) {
 
		try {
			ordersRepository.findById(orderId).orElseThrow(()->new OrderNotFoundException());
			ordersRepository.deleteById(orderId);

		}catch(OrderNotFoundException e) {
			return "Invalid order ID";
		}
		return "Deleted Successfully";
	}

	@Override
	public List<OrdersDTO> getOrderCustomerId(int customerId) {

		List<Orders> orders = ordersRepository.findByCustomerId(customerId);;

		List<OrdersDTO> ordersDTOs = new ArrayList<OrdersDTO>();

		for(Orders order:orders) {
			ordersDTOs.add(mapOrdersToDTO(order));
		}
		return ordersDTOs;

	}

	@Override
	public String orderAccepted(int orderId) {
		
//		int partnerId=deliveryPartnerService.assignDeliveryPartner();
//		if(partnerId==0) {
//			return "No partners available currently.";
//		}
		
		try {
			Orders order=ordersRepository.findById(orderId).orElseThrow(()->new OrderNotFoundException());
//			order.setDeliveryPartnerId(partnerId);
			order.setStatus(Status.Accepted);
			ordersRepository.save(order);
		}catch(OrderNotFoundException e) {
			return "Invalid order ID.";
		}
		return "Order Accepted";
	}
	
	@Override
	public String orderDelivered(int orderId) {
		try {
			Orders order=ordersRepository.findById(orderId).orElseThrow(()->new OrderNotFoundException());
//			deliveryPartnerService.unAssignDeliveryPartner(order.getDeliveryPartnerId());
			order.setStatus(Status.Delivered);
			ordersRepository.save(order);
		}catch(OrderNotFoundException e) {
			return "Invalid order ID.";
		}
		return "Order Delivered";
		
	}
	
	private OrdersDTO mapOrdersToDTO(Orders order) {
		OrdersDTO orderDto= new OrdersDTO();
		orderDto.setOrderId(order.getOrderId());
		orderDto.setCustomerId(order.getCustomerId());
		orderDto.setCartId(order.getCartId());
		orderDto.setCartItems(order.getCartItems());
		orderDto.setDate(order.getDate());
		orderDto.setStatus(order.getStatus());
		orderDto.setTotalPrice(order.getTotalPrice());
		orderDto.setTotalQuantity(order.getTotalQuantity());
//		orderDto.setDeliveryPartnerId(order.getDeliveryPartnerId());
		
		return orderDto;
	}







}
