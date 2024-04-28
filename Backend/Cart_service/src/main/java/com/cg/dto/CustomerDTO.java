package com.cg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

	protected int id;
	
	protected String username;
	
	protected String password;
	
	protected String email;
	
	protected long number;

	protected String address;
}
