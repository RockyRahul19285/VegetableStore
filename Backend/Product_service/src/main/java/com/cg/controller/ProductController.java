package com.cg.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cg.dto.ProductDTO;
import com.cg.exception.NoSuchCategoryException;
import com.cg.exception.ProductNotFoundException;
import com.cg.serviceImpl.ProductServiceImpl;

@RestController
@RequestMapping("/product")
//@CrossOrigin(origins = "*")	//Front-end Connection
public class ProductController {

	@Autowired
	private ProductServiceImpl productServiceImpl;

	@PostMapping("/addProduct")
	public ProductDTO addProduct(@RequestBody ProductDTO productDTO) throws NoSuchCategoryException {
		return productServiceImpl.addProduct(productDTO);
	}
	
	@GetMapping("/getById/{id}")
	public ProductDTO getProductById(@PathVariable(value = "id") int id) throws ProductNotFoundException {
		return productServiceImpl.getById(id);
	}

	@PutMapping("/updateProduct/{no}")
	public String updateProduct(@PathVariable(value = "no") int no, @RequestBody ProductDTO product) {

		return productServiceImpl.updateProduct(no, product);
	}

	@DeleteMapping("/deleteProduct/{no}")
	public boolean deleteProduct(@PathVariable(value = "no") int no) {
		productServiceImpl.deleteProduct(no);

		return true;
	}
//
//	@GetMapping("/getByBrand/{name}")
//	public List<ProductDTO> getProductById(@PathVariable(value = "name") String name) {
//		return productServiceImpl.getProductByBrand(name);
//	}
//	
	@GetMapping("/getAllProducts")
	public List<ProductDTO> getAllProducts() {
		return productServiceImpl.getAllProducts();
	}
	@GetMapping("/getByCategoryId/{id}")
	public List<ProductDTO> getProductByCategoryId(@PathVariable(value = "id") int id) throws NoSuchCategoryException {
		return productServiceImpl.getByCategoryId(id);
	}

	
//	 @PostMapping("/{productId}/upload-image")
//	    public String handleImageUpload(
//	        @PathVariable int productId,
//	        @RequestParam("image") MultipartFile image) {
//
//	        if (image != null) {
//	            try {
//	                // Generate a unique filename for the image to avoid naming conflicts
//	                String filename = UUID.randomUUID() + "_" + image.getOriginalFilename();
//
//	                // Set the path where you want to save the image
//	                String imagePath = "C:/Users/PRAVENKA/Downloads/fdp2/" + filename;
//
//	                File imageFile = new File(imagePath);
//
//	                // Save the image file
//	                image.transferTo(imageFile);
//
//	                // Link the image path to the product in your database
//	                productServiceImpl.linkImageToProduct(productId, imagePath);
//
//	                return "Image uploaded successfully";
//	            } catch (IOException e) {
//	                return "Failed to upload image";
//	            }
//	        } else {
//	            return "No image file provided";
//	        }
//	    }
//	
}

