package com.cg.service;

import java.util.List;

import com.cg.dto.AdminDTO;
import com.cg.entity.Admin;
import com.cg.exception.AdminNotFoundException;

public interface AdminService {

	public Admin addAdmin(AdminDTO adminDTO);
	public String updateAdmin(int id,AdminDTO adminDTO);
	public AdminDTO getAdminByEmail(String email) throws AdminNotFoundException ;
	public String deleteAdmin(int id);
	public List<AdminDTO> readAllAdmins();
	public String login(String email,String password);
}
