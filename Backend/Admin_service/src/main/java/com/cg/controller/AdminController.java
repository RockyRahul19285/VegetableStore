package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.AdminDTO;
import com.cg.entity.Admin;
import com.cg.exception.AdminNotFoundException;
import com.cg.serviceImpl.AdminServiceImpl;

@RestController
@RequestMapping("/admin")
//@CrossOrigin(origins = "*")	//Frontend Connection
public class AdminController {

	@Autowired
	AdminServiceImpl adminServiceImpl;

	@PostMapping("/registerAdmin")
	public Admin addAdmin(@RequestBody AdminDTO adminDTO) {
		return adminServiceImpl.addAdmin(adminDTO);
	}

	@PutMapping("/updateAdmin/{no}")
	public String updateAdmin(@PathVariable(value = "no") int no, @RequestBody AdminDTO admin) {

		return adminServiceImpl.updateAdmin(no, admin);
	}

	@GetMapping("/adminByEmail/{email}")
	public AdminDTO getAdminByEmail(@PathVariable(value = "email") String email) throws AdminNotFoundException {
		return adminServiceImpl.getAdminByEmail(email);
	}
	@GetMapping("/allAdmins")
	public List<AdminDTO> getAllAdmins() {
		return adminServiceImpl.readAllAdmins();
	}
	@DeleteMapping("/deleteadmin/{id}")
	public String deleteAdmin(@PathVariable("id") int id) {
		return adminServiceImpl.deleteAdmin(id);
	}
	@PostMapping("/adminLogin/{email}/{password}")
	public ResponseEntity<String> login(@PathVariable("email")String email, @PathVariable("password")String password){
		return new ResponseEntity<String>(adminServiceImpl.login(email, password),
				HttpStatus.OK);
	}

}


