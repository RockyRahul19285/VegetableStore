package com.cg.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.AdminDTO;
import com.cg.entity.Admin;
import com.cg.exception.AdminNotFoundException;
import com.cg.repository.AdminRepository;
import com.cg.service.AdminService;


@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminRepository adminRepository;

	@Override
	public Admin addAdmin(AdminDTO AdminDTO) {
		Admin admin = new Admin();
		admin.setUsername(AdminDTO.getUsername());
		admin.setAddress(AdminDTO.getAddress());
		admin.setEmail(AdminDTO.getEmail());
		admin.setNumber(AdminDTO.getNumber());
		admin.setPassword(AdminDTO.getPassword());
		

		adminRepository.save(admin);
		return admin;
	}

	// Update User
	@Override
	public String updateAdmin(int id, AdminDTO adminData) {

		try {
			Admin admin = adminRepository.findById(id).orElseThrow(() -> new AdminNotFoundException());
			if (admin.getUsername() != null)
				admin.setUsername(adminData.getUsername());
			if (admin.getNumber() != 0)
				admin.setNumber(adminData.getNumber());
			if (admin.getAddress() != null)
				admin.setAddress(adminData.getAddress());
			if (admin.getEmail() != null)
				admin.setEmail(adminData.getEmail());
			adminRepository.save(admin);

		} catch (AdminNotFoundException e) {
			return "Admin data not updated";
		}
		return "Admin Updated Successfully";
	}

	// Delete user
	@Override
	public String deleteAdmin(int id) {
		try {
			Admin admin= adminRepository.findById(id).orElseThrow(()->new AdminNotFoundException());
			adminRepository.delete(admin);
		}catch(AdminNotFoundException e){
			return "Invalid admin ID...";
		}

		return "Admin deleted successfully!!";
		
	}

	@Override
	public AdminDTO getAdminByEmail(String email) throws AdminNotFoundException {
		Admin admin = adminRepository.findByEmail(email);
		if(admin==null) {
			throw new AdminNotFoundException("Admin not found for given email....");
		}
		AdminDTO AdminDTO = new AdminDTO();

		AdminDTO.setAddress(admin.getAddress());
		AdminDTO.setEmail(admin.getEmail());
		AdminDTO.setNumber(admin.getNumber());
		AdminDTO.setUsername(admin.getUsername());
		AdminDTO.setId(admin.getId());

		return AdminDTO;
	}

	// Read all Users
	@Override
	public List<AdminDTO> readAllAdmins() {
		List<Admin> admins = adminRepository.findAll();

		System.out.println(admins);
		List<AdminDTO> AdminDTOs = new ArrayList<AdminDTO>();
		for (Admin admin : admins) {
			AdminDTO AdminDTO = new AdminDTO();
			AdminDTO.setUsername(admin.getUsername());
			AdminDTO.setAddress(admin.getAddress());
			AdminDTO.setEmail(admin.getEmail());
			AdminDTO.setPassword(admin.getPassword());
			AdminDTO.setNumber(admin.getNumber());
			AdminDTO.setId(admin.getId());
			AdminDTOs.add(AdminDTO);

		}

		return AdminDTOs;
	}

	@Override
	public String login(String email, String password) {
		Admin admin = adminRepository.findByEmail(email);
		if(admin != null) {
			String ipPassword = password;
			String adminPassword = admin.getPassword();
			boolean flag = ipPassword.matches(adminPassword);
			if(flag) {
				return admin.getId()+"";
			}else {
					return "Invalid password";
				}
			}else {
				return "No user found";
		}
	}

}
