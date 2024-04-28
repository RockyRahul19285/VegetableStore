package com.cg.service;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
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
 
import com.cg.dto.DeliveryPartnerDTO;
import com.cg.entity.DeliveryPartner;
import com.cg.entity.PartnerStatus;
import com.cg.exception.DeliveryPartnerNotFoundException;
import com.cg.repository.DeliveryPartnerRepository;
import com.cg.serviceImpl.DeliveryPartnerServiceImpl;
 
class DeliveryPartnerServiceTest {
 
    @Mock
    private DeliveryPartnerRepository deliveryPartnerRepository;
 
    @InjectMocks
    private DeliveryPartnerServiceImpl deliveryPartnerService;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
    void testAddDeliveryPartner() {
        DeliveryPartner deliveryPartner = new DeliveryPartner();
        deliveryPartner.setPartnerName("John Doe");
        deliveryPartner.setPartnerAge(30);
        deliveryPartner.setGender("Male");
        deliveryPartner.setContactNumber(1234567890);
        deliveryPartner.setStatus(PartnerStatus.Not_Assigned);
 
        when(deliveryPartnerRepository.save(any(DeliveryPartner.class))).thenReturn(deliveryPartner);
 
        DeliveryPartnerDTO addedDeliveryPartner = deliveryPartnerService.addDeliveryPartner(deliveryPartner);
 
        assertEquals("John Doe", addedDeliveryPartner.getPartnerName());
        assertEquals(30, addedDeliveryPartner.getPartnerAge());
        assertEquals("Male", addedDeliveryPartner.getGender());
        assertEquals(1234567890, addedDeliveryPartner.getContactNumber());
        assertEquals(PartnerStatus.Not_Assigned, addedDeliveryPartner.getStatus());
 
        verify(deliveryPartnerRepository, times(1)).save(any(DeliveryPartner.class));
    }
 
    @Test
    void testDeleteDeliveryPartner() {
        int id = 1;
        deliveryPartnerService.deleteDeliveryPartner(id);
        verify(deliveryPartnerRepository, times(1)).deleteById(id);
    }
 
    @Test
    void testUpdateDeliveryPartner() throws DeliveryPartnerNotFoundException {
        int id = 1;
        DeliveryPartner deliveryPartner = new DeliveryPartner();
        deliveryPartner.setPartnerName("John Doe");
        deliveryPartner.setPartnerAge(30);
        deliveryPartner.setGender("Male");
        deliveryPartner.setContactNumber(1234567890);
 
        when(deliveryPartnerRepository.findById(id)).thenReturn(Optional.of(deliveryPartner));
 
        String result = deliveryPartnerService.updateDeliveryPartner(id, deliveryPartner);
 
        assertEquals("Delivery partner details updated.", result);
 
        verify(deliveryPartnerRepository, times(1)).findById(id);
        verify(deliveryPartnerRepository, times(1)).save(any(DeliveryPartner.class));
    }
 
    @Test
    void testGetDeliveryPartnerById() throws DeliveryPartnerNotFoundException {
        int id = 1;
        DeliveryPartner deliveryPartner = new DeliveryPartner();
        deliveryPartner.setId(id);
        deliveryPartner.setPartnerName("John Doe");
        deliveryPartner.setPartnerAge(30);
        deliveryPartner.setGender("Male");
        deliveryPartner.setContactNumber(1234567890);
        deliveryPartner.setStatus(PartnerStatus.Not_Assigned);
 
        when(deliveryPartnerRepository.findById(id)).thenReturn(Optional.of(deliveryPartner));
 
        DeliveryPartnerDTO foundDeliveryPartner = deliveryPartnerService.getDeliveryPartnerById(id);
 
        assertEquals("John Doe", foundDeliveryPartner.getPartnerName());
        assertEquals(30, foundDeliveryPartner.getPartnerAge());
        assertEquals("Male", foundDeliveryPartner.getGender());
        assertEquals(1234567890, foundDeliveryPartner.getContactNumber());
        assertEquals(PartnerStatus.Not_Assigned, foundDeliveryPartner.getStatus());
 
        verify(deliveryPartnerRepository, times(1)).findById(id);
    }
 
    @Test
    void testGetAllDeliveryPartners() {
        List<DeliveryPartner> deliveryPartners = new ArrayList<>();
        deliveryPartners.add(new DeliveryPartner());
        deliveryPartners.add(new DeliveryPartner());
 
        when(deliveryPartnerRepository.findAll()).thenReturn(deliveryPartners);
 
        List<DeliveryPartnerDTO> allDeliveryPartners = deliveryPartnerService.getAllDeliveryPartners();
 
        assertEquals(2, allDeliveryPartners.size());
 
        verify(deliveryPartnerRepository, times(1)).findAll();
    }
 
    @Test
    void testAssignDeliveryPartner() {
        DeliveryPartner partner = new DeliveryPartner();
        partner.setId(1);
        partner.setStatus(PartnerStatus.Not_Assigned);
 
        List<DeliveryPartner> partners = new ArrayList<>();
        partners.add(partner);
 
        when(deliveryPartnerRepository.findAll()).thenReturn(partners);
        when(deliveryPartnerRepository.save(any(DeliveryPartner.class))).thenReturn(partner);
 
        int assignedId = deliveryPartnerService.assignDeliveryPartner();
 
        assertEquals(1, assignedId);
        assertEquals(PartnerStatus.Assigned, partner.getStatus());
 
        verify(deliveryPartnerRepository, times(1)).findAll();
        verify(deliveryPartnerRepository, times(1)).save(any(DeliveryPartner.class));
    }
 
    @Test
    void testUnAssignDeliveryPartner() {
        int id = 1;
        DeliveryPartner partner = new DeliveryPartner();
        partner.setId(id);
        partner.setStatus(PartnerStatus.Assigned);
 
        when(deliveryPartnerRepository.findById(id)).thenReturn(Optional.of(partner));
 
        deliveryPartnerService.unAssignDeliveryPartner(id);
 
        assertEquals(PartnerStatus.Not_Assigned, partner.getStatus());
 
        verify(deliveryPartnerRepository, times(1)).findById(id);
        verify(deliveryPartnerRepository, times(1)).save(partner);
    }
}