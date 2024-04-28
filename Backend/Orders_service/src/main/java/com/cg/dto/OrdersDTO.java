package com.cg.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.cg.entity.Status;

import lombok.Data;

@Data
public class OrdersDTO {
	private int orderId;
	
	private LocalDateTime date;
	
	private Status status;

	private int cartId;
	
	private List<Integer> cartItems = new ArrayList<>();

	private int customerId;

	private int totalQuantity;
	
	private double totalPrice;
	
//	private int deliveryPartnerId;
}
