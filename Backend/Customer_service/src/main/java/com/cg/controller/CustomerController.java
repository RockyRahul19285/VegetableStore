package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.CustomerDTO;
import com.cg.entity.Customer;
import com.cg.exception.CustomerNotFoundException;
import com.cg.serviceImpl.CustomerServiceImplementation;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/user")
//@CrossOrigin(origins = "*") // Frontend Connection
public class CustomerController {

	@Autowired
	CustomerServiceImplementation customerServiceImpl;

	@PostMapping("/registerCustomer")
	public ResponseEntity<CustomerDTO> addCustomer(@Valid @RequestBody CustomerDTO customerDTO){
		return new ResponseEntity<CustomerDTO>(customerServiceImpl.registerCustomer(customerDTO),
				HttpStatus.CREATED);
	}

	@GetMapping("/allCustomers")
	public List<CustomerDTO> readAllCustomers() {
		return customerServiceImpl.readAllCustomers();
	}
	
	@PutMapping("/updateCustomer/{no}")
	public String updateCustomer(@PathVariable(value = "no") int no, @RequestBody Customer user) {

		return customerServiceImpl.updateCustomer(no, user);
	}

	@DeleteMapping("/deleteCustomer/{no}")
	public String deleteCustomer(@PathVariable(value = "no") int no) {
		return customerServiceImpl.deleteCustomer(no);
		
	}

	@GetMapping("/CustomerByEmail/{email}")
	public CustomerDTO getCustomerByEmail(@PathVariable(value = "email") String email) throws CustomerNotFoundException {
		return customerServiceImpl.getByEmail(email);
	}

	@GetMapping("/CustomerById/{id}")
	public CustomerDTO getCustomerById(@PathVariable(value = "id") int id) throws CustomerNotFoundException {
		return customerServiceImpl.getCustomerById(id);
	}
	
	@PostMapping("/customerLogin/{email}/{password}")
	public ResponseEntity<String> login(@PathVariable("email")String email,
			@PathVariable("password")String password){
		return new ResponseEntity<String>(customerServiceImpl.login(email, password),
				HttpStatus.OK);
	}

}

