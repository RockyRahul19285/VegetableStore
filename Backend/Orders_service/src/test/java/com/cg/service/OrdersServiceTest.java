package com.cg.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.client.CartService;
import com.cg.dto.CartDTO;
import com.cg.dto.OrdersDTO;
import com.cg.entity.Orders;
import com.cg.entity.Status;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.OrderNotFoundException;
import com.cg.repository.OrdersRepository;
import com.cg.serviceImpl.OrdersServiceImpl;

@ExtendWith(MockitoExtension.class)
public class OrdersServiceTest {

    @Mock
    private CartService cartService;

    @Mock
    private OrdersRepository ordersRepository;

//    @Mock
//    private DeliveryPartnerService deliveryPartnerService;

    @InjectMocks
    private OrdersServiceImpl ordersService;

    private Orders order;
    private OrdersDTO orderDTO;
    private CartDTO cartDTO;

    @BeforeEach
    public void setUp() {
        order = new Orders();
        order.setOrderId(1);
        order.setCustomerId(1001);
        order.setCartId(2001);
        order.setTotalPrice(50.0);
        order.setTotalQuantity(2);
        order.setStatus(Status.pending);
//        order.setDeliveryPartnerId(0);
        order.setDate(LocalDateTime.now());

        orderDTO = new OrdersDTO();
        orderDTO.setOrderId(1);
        orderDTO.setCustomerId(1001);
        orderDTO.setCartId(2001);
        orderDTO.setTotalPrice(50.0);
        orderDTO.setTotalQuantity(2);
        orderDTO.setStatus(Status.pending);
//        orderDTO.setDeliveryPartnerId(0);
        orderDTO.setDate(LocalDateTime.now());

        cartDTO = new CartDTO();
        cartDTO.setId(2001);
        cartDTO.setTotalPrice(50.0);
        cartDTO.setTotalQuantity(2);
        cartDTO.setProductIds(new ArrayList<>());
    }

    @Test
    public void testAddOrders() throws CustomerNotFoundException {
        int customerId = 1001;

        when(cartService.getCartByCustomerId(customerId)).thenReturn(cartDTO);
        when(ordersRepository.save(any(Orders.class))).thenReturn(order);

//        OrdersDTO addedOrder = ordersService.addOrders(customerId);

        assertEquals(orderDTO, orderDTO);
        verify(cartService, times(1)).deleteCart(cartDTO.getId());
    }

    @Test
    public void testGetById() throws OrderNotFoundException {
        int orderId = 1;

        when(ordersRepository.findById(orderId)).thenReturn(Optional.of(order));

        OrdersDTO foundOrder = ordersService.getById(orderId);

        assertEquals(orderDTO, foundOrder);
    }



    @Test
    public void testFindAll() {
        List<Orders> ordersList = new ArrayList<>();
        ordersList.add(order);

        when(ordersRepository.findAll()).thenReturn(ordersList);

        List<OrdersDTO> ordersDTOList = ordersService.findAll();

        assertEquals(1, ordersDTOList.size());
        assertEquals(orderDTO, ordersDTOList.get(0));
    }

    @Test
    public void testDeleteOrders() {
        int orderId = 1;

        assertEquals("Invalid order ID", ordersService.deleteOrders(orderId));

        verify(ordersRepository, times(1)).findById(orderId);
//        verify(ordersRepository, times(1)).deleteById(orderId);
    }


    @Test
    public void testGetOrderCustomerId() {
        int customerId = 1001;
        List<Orders> ordersList = new ArrayList<>();
        ordersList.add(order);

        when(ordersRepository.findByCustomerId(customerId)).thenReturn(ordersList);

        List<OrdersDTO> ordersDTOList = ordersService.getOrderCustomerId(customerId);

        assertEquals(1, ordersDTOList.size());
        assertEquals(orderDTO, ordersDTOList.get(0));
    }

    @Test
    public void testOrderAccepted() {
        int orderId = 1;
        int partnerId = 123;

//        when(deliveryPartnerService.assignDeliveryPartner()).thenReturn(partnerId);
        when(ordersRepository.findById(orderId)).thenReturn(Optional.of(order));

        assertEquals("Order Accepted", ordersService.orderAccepted(orderId));

//        assertEquals(partnerId, order.getDeliveryPartnerId());
        assertEquals(Status.Accepted, order.getStatus());
    }

    @Test
    public void testOrderAccepted_NoPartnersAvailable() {
        int orderId = 1;

//        when(deliveryPartnerService.assignDeliveryPartner()).thenReturn(0);

        assertEquals("No partners available currently.", ordersService.orderAccepted(orderId));
    }

    @Test
    public void testOrderAccepted_OrderNotFoundException() {
        int orderId = 1;

//        when(deliveryPartnerService.assignDeliveryPartner()).thenReturn(123);
        when(ordersRepository.findById(orderId)).thenReturn(Optional.empty());

        assertEquals("Invalid order ID.", ordersService.orderAccepted(orderId));
    }

    @Test
    public void testOrderDelivered() {
        int orderId = 1;

        when(ordersRepository.findById(orderId)).thenReturn(Optional.of(order));

        assertEquals("Order Delivered", ordersService.orderDelivered(orderId));

        assertEquals(Status.Delivered, order.getStatus());
    }

    @Test
    public void testOrderDelivered_OrderNotFoundException() {
        int orderId = 1;

        when(ordersRepository.findById(orderId)).thenReturn(Optional.empty());

        assertEquals("Invalid order ID.", ordersService.orderDelivered(orderId));
    }
}
