package com.cg.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.client.CategoryService;
import com.cg.dto.CategoryDTO;
import com.cg.dto.ProductDTO;
import com.cg.entity.Product;
import com.cg.exception.NoSuchCategoryException;
import com.cg.exception.ProductNotFoundException;
import com.cg.repository.ProductRepository;
import com.cg.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryService categoryService;

	@Override
	public ProductDTO addProduct(ProductDTO productDTO) throws NoSuchCategoryException{
		Product product = new Product();
		
		CategoryDTO category= categoryService.searchCategoryById(productDTO.getCategoryId());
		if(category==null) {
			throw new NoSuchCategoryException("Invalid category id");
		}else {
//			product.setBrand(productDTO.getBrand());
			product.setCategoryId(productDTO.getCategoryId());
//			product.setProductImage(productDTO.getProductImage());
			product.setProductName(productDTO.getProductName());
			product.setProductPrice(productDTO.getProductPrice());

			productRepository.save(product);
			
			product.setProductId(productDTO.getProductId());
			return productDTO;
		}
		
		
	}

	@Override
	public ProductDTO getById(int id) throws ProductNotFoundException{
		
		Product product = productRepository.findById(id).get();
		if(product==null) {
			throw new ProductNotFoundException("Invalid product Id");
		}else {
			ProductDTO productDTO = new ProductDTO();
//			productDTO.setBrand(product.getBrand());
			productDTO.setCategoryId(product.getCategoryId());
			productDTO.setProductId(product.getProductId());
//			productDTO.setProductImage(product.getProductImage());
			productDTO.setProductName(product.getProductName());
			productDTO.setProductPrice(product.getProductPrice());

			return productDTO;
		}

	}

	@Override
	public String updateProduct(int id, ProductDTO productDTO) {

		Product product;
		try {
			product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException());

			if (product.getProductName() != null)
				product.setProductName(productDTO.getProductName());
//			if (product.getBrand() != null)
//				product.setBrand(productDTO.getBrand());
			if (product.getCategoryId() != 0)
				product.setCategoryId(productDTO.getCategoryId());
//			if (product.getProductImage() != null)
//				product.setProductImage(productDTO.getProductImage());
			if (product.getProductPrice() != 0)
				product.setProductPrice(productDTO.getProductPrice());

			productRepository.save(product);
		} catch (ProductNotFoundException e) {
			System.out.println(e);
			return "Product data not updated";
		}
		return "Product updated Successfully";

	}

	@Override
	public String deleteProduct(int id) {
		
		try {
			Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException());
			productRepository.delete(product);
		}catch(ProductNotFoundException e) {
			return "Invalid Product id";
		}
		return "Product deleted";

	}


//	@Override
//	public List<ProductDTO> getProductByBrand(String brandName) {
//
//		List<Product> products = productRepository.findByBrand(brandName);
//
//		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
//
//		for (Product product : products) {
//			ProductDTO productDTO = new ProductDTO();
//
//			productDTO.setCategoryId(product.getCategoryId());
//			productDTO.setBrand(brandName);
//			productDTO.setProductId(product.getProductId());
//			productDTO.setProductImage(product.getProductImage());
//			productDTO.setProductName(product.getProductName());
//			productDTO.setProductPrice(product.getProductPrice());
//
//			productDTOs.add(productDTO);
//		}
//
//		return productDTOs;
//
//	}

	@Override
	public List<ProductDTO> getAllProducts() {

		List<Product> products = productRepository.findAll();

		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();

		for (Product product : products) {
			ProductDTO productDTO = new ProductDTO();

			productDTO.setCategoryId(product.getCategoryId());
//			productDTO.setBrand(product.getBrand());
			productDTO.setProductId(product.getProductId());
//			productDTO.setProductImage(product.getProductImage());
			productDTO.setProductName(product.getProductName());
			productDTO.setProductPrice(product.getProductPrice());

			productDTOs.add(productDTO);
		}

		return productDTOs;

	}

	@Override
	public List<ProductDTO> getByCategoryId(int id) throws NoSuchCategoryException{

		CategoryDTO category= categoryService.searchCategoryById(id);
		if(category==null) {
			throw new NoSuchCategoryException("Invalid category id");
		}
		
		List<Product> products = productRepository.findByCategoryId(id);
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		for (Product product : products) {

			ProductDTO productDTO = new ProductDTO();
//			productDTO.setBrand(product.getBrand());
			productDTO.setCategoryId(product.getCategoryId());
			productDTO.setProductId(product.getProductId());
//			productDTO.setProductImage(product.getProductImage());
			productDTO.setProductName(product.getProductName());
			productDTO.setProductPrice(product.getProductPrice());

			productDTOs.add(productDTO);
		}

		return productDTOs;

	}

//	public void linkImageToProduct(int productId, String imagePath) {
//		// TODO Auto-generated method stub
//		Product product = productRepository.findById(productId).get();
//		product.setProductImage(imagePath);
//
//		productRepository.save(product);
//
//	}

}
