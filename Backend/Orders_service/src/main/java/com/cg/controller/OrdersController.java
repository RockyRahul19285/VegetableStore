package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.OrdersDTO;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.OrderNotFoundException;
import com.cg.service.OrdersService;

@RestController
@RequestMapping("/orders")
//@CrossOrigin(origins = "*")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @PostMapping("/add/{customerId}")
    public OrdersDTO addOrders(@PathVariable("customerId") int customerId) throws CustomerNotFoundException {
        return ordersService.addOrders(customerId);
    }

    @GetMapping("getById/{id}")
    public OrdersDTO getOrderById(@PathVariable("id") int id) throws OrderNotFoundException {
        return ordersService.getById(id);
    }

    @GetMapping("/findAll")
    public List<OrdersDTO> getAllOrders() {
        return ordersService.findAll();
    }

    @DeleteMapping("/delete/{orderId}")
    public String deleteOrders(@PathVariable("orderId") int orderId) {
        return ordersService.deleteOrders(orderId);
    }

    @PutMapping("/accept/{orderId}")
    public String acceptOrder(@PathVariable("orderId") int orderId) {
        return ordersService.orderAccepted(orderId);
    }

    @PutMapping("/deliver/{orderId}")
    public String deliverOrder(@PathVariable("orderId") int orderId) {
        return ordersService.orderDelivered(orderId);
    }
    @GetMapping("/getByCustomerId/{customerId}")
    public ResponseEntity<List<OrdersDTO>> fetchOrdersByCustId(@PathVariable("customerId") int customerId){
    	return new ResponseEntity<List<OrdersDTO>>(ordersService.getOrderCustomerId(customerId),
    			HttpStatus.OK);
    }
}

