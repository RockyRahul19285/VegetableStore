package com.cg.service;

import java.util.List;
import java.util.Optional;

import com.cg.dto.CustomerDTO;
import com.cg.entity.Customer;
import com.cg.exception.CustomerNotFoundException;


public interface CustomerService {

	public CustomerDTO registerCustomer(CustomerDTO customerDTO);
	
//	public Optional<CustomerDTO> addCustomer(CustomerDTO customerDTO);

	public String updateCustomer(int id, Customer customer);

	public String deleteCustomer(int id);

	public CustomerDTO getByEmail(String email) throws CustomerNotFoundException;

	public CustomerDTO getCustomerById(int id) throws CustomerNotFoundException;
	
	public List<CustomerDTO> readAllCustomers();
	
	public String login(String email,String password);
}
