package com.cg.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
 
import com.cg.dto.AdminDTO;
import com.cg.entity.Admin;
import com.cg.exception.AdminNotFoundException;
import com.cg.repository.AdminRepository;
import com.cg.serviceImpl.AdminServiceImpl;
@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {
    @Mock
    private AdminRepository adminRepository;
    @InjectMocks
    private AdminServiceImpl adminService;

    @Test
    public void testUpdateAdmin() {
        int idToUpdate = 1;
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setUsername("updateduser");
        Admin adminToUpdate = new Admin();
        adminToUpdate.setId(idToUpdate);
        adminToUpdate.setUsername("originaluser");
        when(adminRepository.findById(idToUpdate)).thenReturn(Optional.of(adminToUpdate));
        when(adminRepository.save(any(Admin.class))).thenAnswer(invocation -> invocation.getArgument(0));
        String result = adminService.updateAdmin(idToUpdate, adminDTO);
        assertEquals("Admin Updated Successfully", result);
        assertEquals("updateduser", adminToUpdate.getUsername());
    }
    @Test
    public void testDeleteAdmin() {
        int idToDelete = 1;
        Admin adminToDelete = new Admin();
        adminToDelete.setId(idToDelete);
        when(adminRepository.findById(idToDelete)).thenReturn(Optional.of(adminToDelete));
        String result = adminService.deleteAdmin(idToDelete);
        assertEquals("Admin deleted successfully!!", result);
        verify(adminRepository, times(1)).delete(adminToDelete);
    }
    @Test
    public void testGetAdminByEmail() throws AdminNotFoundException {
        String emailToFind = "test@example.com";
        Admin adminFound = new Admin();
        adminFound.setEmail(emailToFind);
        when(adminRepository.findByEmail(emailToFind)).thenReturn(adminFound);
        AdminDTO foundAdminDTO = adminService.getAdminByEmail(emailToFind);
        assertNotNull(foundAdminDTO);
        assertEquals(emailToFind, foundAdminDTO.getEmail());
    }
//
// 
//    @Test
//    public void testAddAdmin() {
//        Admin adminToAdd = new Admin();
//        adminToAdd.setUsername("testuser");
//        adminToAdd.setEmail("test@example.com");
//        adminToAdd.setAddress("123 Test St");
//        adminToAdd.setNumber(1234567890);
//        when(adminRepository.save(any(Admin.class))).thenReturn(adminToAdd);
//    }
    @Test
    public void testReadAllAdmins() {
        List<Admin> admins = new ArrayList<>();
        Admin admin1 = new Admin();
        admin1.setUsername("user1");
        admin1.setEmail("user1@example.com");
        admin1.setAddress("123 Test St");
        admin1.setNumber(1234567890);
        Admin admin2 = new Admin();
        admin2.setUsername("user2");
        admin2.setEmail("user2@example.com");
        admin2.setAddress("456 Test St");
        admin2.setNumber(987654321);
        admins.add(admin1);
        admins.add(admin2);
        when(adminRepository.findAll()).thenReturn(admins);
        List<AdminDTO> adminDTOs = adminService.readAllAdmins();
        assertNotNull(adminDTOs);
        assertEquals(2, adminDTOs.size());
        assertEquals("user1", adminDTOs.get(0).getUsername());
        assertEquals("user1@example.com", adminDTOs.get(0).getEmail());
        assertEquals("123 Test St", adminDTOs.get(0).getAddress());
        assertEquals(1234567890, adminDTOs.get(0).getNumber());
        assertEquals("user2", adminDTOs.get(1).getUsername());
        assertEquals("user2@example.com", adminDTOs.get(1).getEmail());
        assertEquals("456 Test St", adminDTOs.get(1).getAddress());
        assertEquals(987654321, adminDTOs.get(1).getNumber());
    }
}
