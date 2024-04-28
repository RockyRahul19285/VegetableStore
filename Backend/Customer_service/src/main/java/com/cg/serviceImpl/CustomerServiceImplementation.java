package com.cg.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.CustomerDTO;
import com.cg.entity.Customer;
import com.cg.exception.CustomException;
import com.cg.exception.CustomerNotFoundException;
import com.cg.repository.CustomerRepository;
import com.cg.service.CustomerService;

@Service
public class CustomerServiceImplementation implements CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public CustomerDTO registerCustomer(CustomerDTO customerDTO) {
	    // Check if the email is already registered
	    Customer existingCustomer = customerRepository.findByEmail(customerDTO.getEmail());
	    if (existingCustomer != null) {
	        throw new CustomException("Email " + customerDTO.getEmail() + " already registered");
	    }

	    // Map properties from CustomerDTO to Customer
	    Customer customer = new Customer();
	    customer.setUsername(customerDTO.getUsername());
	    customer.setEmail(customerDTO.getEmail());
	    customer.setPassword(customerDTO.getPassword());
	    customer.setNumber(customerDTO.getNumber());
	    customer.setAddress(customerDTO.getAddress());

	    // Save the customer
	    Customer savedCustomer = customerRepository.save(customer);

	    // Map properties from saved Customer to CustomerDTO
	    CustomerDTO savedCustomerDTO = new CustomerDTO();
	    savedCustomerDTO.setUsername(savedCustomer.getUsername());
	    savedCustomerDTO.setEmail(savedCustomer.getEmail());
	    savedCustomerDTO.setPassword(savedCustomer.getPassword());
	    savedCustomerDTO.setNumber(savedCustomer.getNumber());
	    savedCustomerDTO.setAddress(savedCustomer.getAddress());

	    return savedCustomerDTO;
	}


	// Read all Users
	@Override
	public List<CustomerDTO> readAllCustomers() {
		List<Customer> customers = customerRepository.findAll();
		List<CustomerDTO> CustomerDTOs = new ArrayList<CustomerDTO>();
		for (Customer customer : customers) {
			CustomerDTO CustomerDTO = mapCustomerToDTO(customer);

			CustomerDTOs.add(CustomerDTO);

		}

		return CustomerDTOs;
	}

	// Update User
	@Override
	public String updateCustomer(int id, Customer user) {

		Customer customer;
		try {
			customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException());

			if (user.getUsername() != null)
				customer.setUsername(user.getUsername());

			if (user.getNumber() != 0)
				customer.setNumber(user.getNumber());

			if (user.getAddress() != null)
				customer.setAddress(user.getAddress());

			if (user.getEmail() != null)
				customer.setEmail(user.getEmail());

			customerRepository.save(customer);
		} catch (CustomerNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			return "Invalid customer.Customer data not updated";

		}

		return "Customer Updated Successfully";

	}

	// Delete user
	@Override
	public String deleteCustomer(int id) {
		try {
			customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException());
			customerRepository.deleteById(id);

		} catch (CustomerNotFoundException e) {
			System.out.println(e);
			return "Invalid customer id";
		}

		return "Customer deleted successfully.";
	}

	@Override
	public CustomerDTO getByEmail(String email) throws CustomerNotFoundException {
		Customer customer = customerRepository.findByEmail(email);
		if (customer == null) {
			throw new CustomerNotFoundException("Customer not found for given email....");
		}
		CustomerDTO customerDTO = new CustomerDTO();

		customerDTO.setAddress(customer.getAddress());
		customerDTO.setEmail(customer.getEmail());
		customerDTO.setNumber(customer.getNumber());
		customerDTO.setUsername(customer.getUsername());
		customerDTO.setId(customer.getId());

		return customerDTO;
	}

	@Override
	public CustomerDTO getCustomerById(int id) throws CustomerNotFoundException {
		Customer customer = customerRepository.findById(id).get();
		if (customer == null) {
			throw new CustomerNotFoundException("Invalid customer ID");
		}

		CustomerDTO dtoUser = mapCustomerToDTO(customer);

		return dtoUser;
	}

	private CustomerDTO mapCustomerToDTO(Customer customer) {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setId(customer.getId());
		customerDTO.setAddress(customer.getAddress());
		customerDTO.setEmail(customer.getEmail());
		customerDTO.setNumber(customer.getNumber());
		customerDTO.setPassword(customer.getPassword());
		customerDTO.setUsername(customer.getUsername());
		return customerDTO;
	}

//	@Override
//	public Optional<CustomerDTO> addCustomer(CustomerDTO customerDTO) {
//		Customer customer = new Customer();
//		customer.setId(customerDTO.getId());
//		customer.setUsername(customerDTO.getUsername());
//		customer.setEmail(customerDTO.getEmail());
//		customer.setPassword(customerDTO.getPassword());
//		customer.setNumber(customerDTO.getNumber());
//		customer.setAddress(customerDTO.getAddress());
//		return Optional.of(customerDTO);
//	}

	@Override
	public String login(String email, String password) {
		Customer customer = customerRepository.findByEmail(email);
		if (customer != null) {
			String ipPassword = password;
			String adminPassword = customer.getPassword();
			boolean flag = ipPassword.matches(adminPassword);
			if (flag) {
				return customer.getId()+"";
			} else {
				return "Invalid password";
			}
		} else {
			return "No user found";
		}
	}
}
