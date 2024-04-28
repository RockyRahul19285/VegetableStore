package com.cg.dto;

import lombok.Data;

@Data
public class ProductDTO {
	private int productId;
	
	private String productName;
	
	private int categoryId;

	private double productPrice;
	
	private String productImage;
	
	private String brand;
}
