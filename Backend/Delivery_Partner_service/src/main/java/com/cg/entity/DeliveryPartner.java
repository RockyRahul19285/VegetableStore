package com.cg.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "deliverypartnerdb")
public class DeliveryPartner {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String partnerName;
	private int partnerAge;
	private String gender;
	private long contactNumber;
	@Enumerated(EnumType.STRING)
	private PartnerStatus status;
}
