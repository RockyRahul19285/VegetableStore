package com.cg.service;

import java.util.List;

import com.cg.dto.DeliveryPartnerDTO;
import com.cg.entity.DeliveryPartner;
import com.cg.exception.DeliveryPartnerNotFoundException;

public interface DeliveryPartnerService {

	public DeliveryPartnerDTO addDeliveryPartner(DeliveryPartner deliveryPartner);
	
	public String deleteDeliveryPartner(int id);
	
	public String updateDeliveryPartner(int id,DeliveryPartner deliveryPartner) throws DeliveryPartnerNotFoundException;
	
	public DeliveryPartnerDTO getDeliveryPartnerById(int id)throws DeliveryPartnerNotFoundException;
	
	public List<DeliveryPartnerDTO> getAllDeliveryPartners();
	
	public int assignDeliveryPartner();
	
	public void unAssignDeliveryPartner(int id);
}
