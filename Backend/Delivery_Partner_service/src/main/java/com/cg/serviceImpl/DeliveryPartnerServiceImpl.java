package com.cg.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.DeliveryPartnerDTO;
import com.cg.entity.DeliveryPartner;
import com.cg.entity.PartnerStatus;
import com.cg.exception.DeliveryPartnerNotFoundException;
import com.cg.repository.DeliveryPartnerRepository;
import com.cg.service.DeliveryPartnerService;

@Service
public class DeliveryPartnerServiceImpl implements DeliveryPartnerService {

	@Autowired
	DeliveryPartnerRepository deliveryPartnerRepository;

	@Override
	public DeliveryPartnerDTO addDeliveryPartner(DeliveryPartner deliveryPartner) {

		deliveryPartner.setStatus(PartnerStatus.Not_Assigned);
		DeliveryPartner partner = deliveryPartnerRepository.save(deliveryPartner);

		return mapPartnerToDto(partner);
	}

	@Override
	public String deleteDeliveryPartner(int id) {
		deliveryPartnerRepository.deleteById(id);

		return "Delivery partner deleted successfully.";
	}

	@Override
	public String updateDeliveryPartner(int id, DeliveryPartner deliveryPartner)
			throws DeliveryPartnerNotFoundException {
		
		try {
			DeliveryPartner partner=deliveryPartnerRepository.findById(id).orElseThrow(() -> new DeliveryPartnerNotFoundException());
			partner.setPartnerName(deliveryPartner.getPartnerName());
			partner.setPartnerAge(deliveryPartner.getPartnerAge());
			partner.setGender(deliveryPartner.getGender());
			partner.setContactNumber(deliveryPartner.getContactNumber());
			deliveryPartnerRepository.save(partner);
			
		}catch(DeliveryPartnerNotFoundException e) {
			return "Invalid delivery partner Id";
		}

		return "Delivery partner details updated.";
	}

	@Override
	public DeliveryPartnerDTO getDeliveryPartnerById(int id) throws DeliveryPartnerNotFoundException {
		DeliveryPartner partner= deliveryPartnerRepository.findById(id).get();
		if(partner== null) {
			throw new DeliveryPartnerNotFoundException("Invalid delivery partner ID.");
		}
		
		return mapPartnerToDto(partner);
	}

	@Override
	public List<DeliveryPartnerDTO> getAllDeliveryPartners() {

		List<DeliveryPartner> partners=deliveryPartnerRepository.findAll();
		List<DeliveryPartnerDTO> partnersDto= new ArrayList<>();
		for(DeliveryPartner partner:partners) {
			partnersDto.add(mapPartnerToDto(partner));
		}
		
		return partnersDto;
	}

	@Override
	public int assignDeliveryPartner() {
		List<DeliveryPartner> partners=deliveryPartnerRepository.findAll();
		int assignedId=0;
		for(DeliveryPartner partner:partners) {
			if(partner.getStatus()==PartnerStatus.Not_Assigned) {
				partner.setStatus(PartnerStatus.Assigned);
				deliveryPartnerRepository.save(partner);
				assignedId=partner.getId();
				break;
			}
		}
		
		return assignedId;
	}
	
	@Override
	public void unAssignDeliveryPartner(int id) {
		DeliveryPartner partner= deliveryPartnerRepository.findById(id).get();
		partner.setStatus(PartnerStatus.Not_Assigned);
		deliveryPartnerRepository.save(partner);
	}

	private DeliveryPartnerDTO mapPartnerToDto(DeliveryPartner partner) {
		DeliveryPartnerDTO partnerDto = new DeliveryPartnerDTO();
		partnerDto.setId(partner.getId());
		partnerDto.setPartnerName(partner.getPartnerName());
		partnerDto.setPartnerAge(partner.getPartnerAge());
		partnerDto.setGender(partner.getGender());
		partnerDto.setContactNumber(partner.getContactNumber());
		partnerDto.setStatus(partner.getStatus());

		return partnerDto;
	}



}
