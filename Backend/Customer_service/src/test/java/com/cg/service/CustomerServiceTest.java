package com.cg.service;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.times;

import static org.mockito.Mockito.verify;

import static org.mockito.Mockito.when;
 
import java.util.ArrayList;

import java.util.List;

import java.util.Optional;
 
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import com.cg.dto.CustomerDTO;
import com.cg.entity.Customer;

import com.cg.exception.CustomerNotFoundException;

import com.cg.repository.CustomerRepository;

import com.cg.serviceImpl.CustomerServiceImplementation;
 
class CustomerServiceTest {
 
    @Mock

    private CustomerRepository customerRepository;
 
    @InjectMocks

    private CustomerServiceImplementation customerService;
 
    @BeforeEach

    void setUp() {

        MockitoAnnotations.openMocks(this);

    }
 
    @Test
    void testRegisterCustomer() {
        // Create a CustomerDTO object
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setUsername("John");
        customerDTO.setEmail("john@example.com");
        customerDTO.setPassword("password123");
        customerDTO.setNumber(1234567890);
        customerDTO.setAddress("123 Street, City");

     // Mock the behavior of customerRepository.save to return the same Customer object
        when(customerRepository.save(any(Customer.class)))
                .thenAnswer(invocation -> {
                    Customer savedCustomer = invocation.getArgument(0);
                    // Simulate the behavior of saving and generating an ID
                    savedCustomer.setId(1); // Assuming ID is generated during save
                    return savedCustomer;
                });

        // Call the method under test
        CustomerDTO registeredCustomerDTO = customerService.registerCustomer(customerDTO);

        // Assertions
        assertEquals("John", registeredCustomerDTO.getUsername());
        assertEquals("password123", registeredCustomerDTO.getPassword()); // Checking password
        assertEquals("john@example.com", registeredCustomerDTO.getEmail());
        assertEquals(1234567890, registeredCustomerDTO.getNumber());
        assertEquals("123 Street, City", registeredCustomerDTO.getAddress());

        // Verify that customerRepository.save was called once with any Customer object
        verify(customerRepository, times(1)).save(any(Customer.class));
    }


 
    @Test

    void testReadAllCustomers() {

        List<Customer> customers = new ArrayList<>();

        customers.add(new Customer());

        customers.add(new Customer());
 
        when(customerRepository.findAll()).thenReturn(customers);
 
        List<CustomerDTO> customerDTOs = customerService.readAllCustomers();
 
        assertEquals(2, customerDTOs.size());
 
        verify(customerRepository, times(1)).findAll();

    }
 
    @Test

    void testUpdateCustomer() {

        int id = 1;

        Customer existingCustomer = new Customer();

        existingCustomer.setId(id);

        existingCustomer.setUsername("Existing");

        existingCustomer.setEmail("existing@example.com");

        existingCustomer.setNumber(1234567890);

        existingCustomer.setAddress("Existing Address");
 
        Customer updatedCustomer = new Customer();

        updatedCustomer.setId(id);

        updatedCustomer.setUsername("Updated");

        updatedCustomer.setEmail("updated@example.com");

        updatedCustomer.setNumber(987654321);

        updatedCustomer.setAddress("Updated Address");
 
        when(customerRepository.findById(id)).thenReturn(Optional.of(existingCustomer));

        when(customerRepository.save(any(Customer.class))).thenReturn(updatedCustomer);
 
        String result = customerService.updateCustomer(id, updatedCustomer);
 
        assertEquals("Customer Updated Successfully", result);

        assertEquals("Updated", existingCustomer.getUsername());

        assertEquals("updated@example.com", existingCustomer.getEmail());

        assertEquals(987654321, existingCustomer.getNumber());

        assertEquals("Updated Address", existingCustomer.getAddress());
 
        verify(customerRepository, times(1)).findById(id);

        verify(customerRepository, times(1)).save(any(Customer.class));

    }
 
    @Test

    void testDeleteCustomer() {

        int id = 1;

        Customer existingCustomer = new Customer();

        existingCustomer.setId(id);
 
        when(customerRepository.findById(id)).thenReturn(Optional.of(existingCustomer));
 
        String result = customerService.deleteCustomer(id);
 
        assertEquals("Customer deleted successfully.", result);
 
        verify(customerRepository, times(1)).findById(id);

        verify(customerRepository, times(1)).deleteById(id);

    }
 
    @Test

    void testGetByEmail() throws CustomerNotFoundException {

    	String emailToFind = "test@example.com";
        Customer customerFound = new Customer();
        customerFound.setEmail(emailToFind);
        when(customerRepository.findByEmail(emailToFind)).thenReturn(customerFound);
        CustomerDTO foundCustomerDTO = customerService.getByEmail(emailToFind);
        assertNotNull(foundCustomerDTO);
        assertEquals(emailToFind, foundCustomerDTO.getEmail());

    }
 
    @Test

    void testGetCustomerById() throws CustomerNotFoundException {

        int id = 1;

        Customer customer = new Customer();

        customer.setId(id);

        customer.setUsername("Test");

        customer.setEmail("test@example.com");
 
        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));
 
    //    assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerById(1));
 
        CustomerDTO customerDTO = customerService.getCustomerById(id);
 
        assertEquals("Test", customerDTO.getUsername());

        assertEquals("test@example.com", customerDTO.getEmail());
 
        verify(customerRepository, times(1)).findById(id);

    }

}

