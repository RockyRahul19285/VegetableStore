package com.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entity.Orders;
import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer>{
	public List<Orders> findByCustomerId(int customerId);
}
