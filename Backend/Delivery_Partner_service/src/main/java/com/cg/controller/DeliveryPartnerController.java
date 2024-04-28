package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.DeliveryPartnerDTO;
import com.cg.entity.DeliveryPartner;
import com.cg.exception.DeliveryPartnerNotFoundException;
import com.cg.serviceImpl.DeliveryPartnerServiceImpl;

@RestController
@RequestMapping("/deliveryPartner")
@CrossOrigin(origins = "*")
public class DeliveryPartnerController {

	 @Autowired
	    private DeliveryPartnerServiceImpl deliveryPartnerService;

	    @PostMapping("/add")
	    public DeliveryPartnerDTO addDeliveryPartner(@RequestBody DeliveryPartner deliveryPartner) {
	        return deliveryPartnerService.addDeliveryPartner(deliveryPartner);
	    }

	    @PutMapping("/update/{id}")
	    public String updateDeliveryPartner(@PathVariable int id, @RequestBody DeliveryPartner deliveryPartner) {
	        try {
	            return deliveryPartnerService.updateDeliveryPartner(id, deliveryPartner);
	        } catch (DeliveryPartnerNotFoundException e) {
	            return e.getMessage();
	        }
	    }

	    @DeleteMapping("/delete/{id}")
	    public String deleteDeliveryPartner(@PathVariable("id") int id) {
	        return deliveryPartnerService.deleteDeliveryPartner(id);
	    }

	    @GetMapping("/getById/{id}")
	    public DeliveryPartnerDTO getDeliveryPartnerById(@PathVariable("id") int id) throws DeliveryPartnerNotFoundException {
	        return deliveryPartnerService.getDeliveryPartnerById(id);
	    }

	    @GetMapping("/findAll")
	    public List<DeliveryPartnerDTO> getAllDeliveryPartners() {
	        return deliveryPartnerService.getAllDeliveryPartners();
	    }

	    @GetMapping("/assign")
	    public int assignDeliveryPartner() {
	        return deliveryPartnerService.assignDeliveryPartner();
	    }
	    
	    @GetMapping("/unassign/{id}")
	    public void unAssignDeliveryPartner(@PathVariable("id") int id) {
	    	 deliveryPartnerService.unAssignDeliveryPartner(id);
	    }
}
