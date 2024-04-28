package com.cg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>{

	// Custom Repository method
//	@Query(value = "select * from products where category_id=?1", nativeQuery = true)
	public List<Product> findByCategoryId(int categoryId);
}
