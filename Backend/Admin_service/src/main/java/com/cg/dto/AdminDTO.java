package com.cg.dto;

import lombok.Data;

@Data
public class AdminDTO {
	private int id;
	private String username;
	private String password;
	private String email;
	private long number;
	private String address;
}
