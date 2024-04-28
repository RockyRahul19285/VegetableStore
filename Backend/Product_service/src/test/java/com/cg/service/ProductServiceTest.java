package com.cg.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.client.CategoryService;
import com.cg.dto.CategoryDTO;
import com.cg.dto.ProductDTO;
import com.cg.entity.Product;
import com.cg.exception.NoSuchCategoryException;
import com.cg.exception.ProductNotFoundException;
import com.cg.repository.ProductRepository;
import com.cg.serviceImpl.ProductServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;
    private ProductDTO productDTO;

    @BeforeEach
    public void setUp() {
        product = new Product();
        product.setProductId(1);
        product.setCategoryId(101);
//        product.setBrand("BrandName");
        product.setProductName("ProductName");
        product.setProductPrice(10.5);
//        product.setProductImage("product_image.jpg");

        productDTO = new ProductDTO();
        productDTO.setProductId(1);
        productDTO.setCategoryId(101);
        productDTO.setBrand("BrandName");
        productDTO.setProductName("ProductName");
        productDTO.setProductPrice(10.5);
        productDTO.setProductImage("product_image.jpg");
    }

    @Test
    public void testAddProduct() throws NoSuchCategoryException {
        when(categoryService.searchCategoryById(101)).thenReturn(new CategoryDTO());
        when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductDTO addedProductDTO = productService.addProduct(productDTO);

        assertEquals(productDTO, addedProductDTO);
    }



    @Test
    public void testGetById() throws ProductNotFoundException {
        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        ProductDTO foundProductDTO = productService.getById(1);

        assertEquals(productDTO, foundProductDTO);
    }

    @Test
    public void testGetById_ProductNotFoundException() {
        when(productRepository.findById(1)).thenReturn(Optional.empty());

        try {
            productService.getById(1);
        } catch (ProductNotFoundException e) {
            assertEquals("Invalid product Id", e.getMessage());
        } catch (NoSuchElementException e) {
        	assertEquals("No value present", e.getMessage());
        }
    }

    @Test
    public void testUpdateProduct() {
        ProductDTO updatedProductDTO = new ProductDTO();
        updatedProductDTO.setProductName("UpdatedProductName");

        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        String result = productService.updateProduct(1, updatedProductDTO);

        assertEquals("Product updated Successfully", result);
        assertEquals("UpdatedProductName", product.getProductName());
    }

    @Test
    public void testDeleteProduct() {
        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        String result = productService.deleteProduct(1);

        assertEquals("Product deleted", result);
        verify(productRepository, times(1)).delete(product);
    }

    @Test
    public void testDeleteProduct_ProductNotFoundException() {
        when(productRepository.findById(1)).thenReturn(Optional.empty());

        String result = productService.deleteProduct(1);

        assertEquals("Invalid Product id", result);
    }

//    @Test
//    public void testGetProductByBrand() {
//        List<Product> productList = new ArrayList<>();
//        productList.add(product);
//
//        when(productRepository.findByBrand("BrandName")).thenReturn(productList);
//
//        List<ProductDTO> productDTOList = productService.getProductByBrand("BrandName");
//
//        assertEquals(1, productDTOList.size());
//        assertEquals(productDTO, productDTOList.get(0));
//    }

    @Test
    public void testGetAllProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        when(productRepository.findAll()).thenReturn(productList);

        List<ProductDTO> productDTOList = productService.getAllProducts();

        assertEquals(1, productDTOList.size());
        assertEquals(productDTO, productDTOList.get(0));
    }

    @Test
    public void testGetByCategoryId() throws NoSuchCategoryException {
        when(categoryService.searchCategoryById(101)).thenReturn(new CategoryDTO());
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        when(productRepository.findByCategoryId(101)).thenReturn(productList);

        List<ProductDTO> productDTOList = productService.getByCategoryId(101);

        assertEquals(1, productDTOList.size());
        assertEquals(productDTO, productDTOList.get(0));
    }


}
