package com.cg.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class CartDTO {

	private int id;

	private List<Integer> productIds = new ArrayList<>();

	private Integer customerId;

	private int totalQuantity;
	private double totalPrice;
}
