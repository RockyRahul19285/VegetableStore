package com.cg.service;

import java.util.List;

import com.cg.dto.ProductDTO;
import com.cg.exception.NoSuchCategoryException;
import com.cg.exception.ProductNotFoundException;


public interface ProductService {
	public ProductDTO getById(int id) throws ProductNotFoundException;
	public String updateProduct(int id,ProductDTO productDTO);
	public String deleteProduct(int no);
	public ProductDTO addProduct(ProductDTO productDTO) throws NoSuchCategoryException;
//	public List<ProductDTO> getProductByBrand(String brandName);
	public List<ProductDTO> getAllProducts();
	public List<ProductDTO> getByCategoryId(int id) throws NoSuchCategoryException;
}
