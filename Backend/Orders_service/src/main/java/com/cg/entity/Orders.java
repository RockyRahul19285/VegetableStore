package com.cg.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ordersdb")
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderId;
	
	private LocalDateTime date;
	@Enumerated(EnumType.STRING)
	private Status status;

	private int cartId;
	
	private List<Integer> cartItems = new ArrayList<>();

	private int customerId;

	private int totalQuantity;
	
	private double totalPrice;
	
//	private int deliveryPartnerId;
}
