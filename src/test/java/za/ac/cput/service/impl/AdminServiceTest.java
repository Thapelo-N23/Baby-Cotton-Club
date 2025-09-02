//package za.ac.cput.service.impl;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import za.ac.cput.domain.Admin;
//import za.ac.cput.domain.UserRole;
//import za.ac.cput.repository.AdminRepository;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class AdminServiceTest {
//    @Mock
//    private AdminRepository adminRepository;
//
//    @InjectMocks
//    private AdminService adminService;
//
//    private Admin admin;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        admin = new Admin.Builder()
//                .adminId(1)
//                .username("adminuser")
//                .password("hashedpassword")
//                .email("admin@example.com")
//                .role(UserRole.ADMIN)
//                .build();
//    }
//
//    @Test
//    void testCreate() {
//        when(adminRepository.save(admin)).thenReturn(admin);
//        Admin created = adminService.create(admin);
//        assertNotNull(created);
//        assertEquals(admin.getUsername(), created.getUsername());
//    }
//
//    @Test
//    void testRead() {
//        when(adminRepository.findById(1)).thenReturn(Optional.of(admin));
//        Admin found = adminService.read(1);
//        assertNotNull(found);
//        assertEquals(admin.getEmail(), found.getEmail());
//    }
//
//    @Test
//    void testUpdate() {
//        when(adminRepository.save(admin)).thenReturn(admin);
//        Admin updated = adminService.update(admin);
//        assertNotNull(updated);
//        assertEquals(admin.getEmail(), updated.getEmail());
//    }
//
//    @Test
//    void testDelete() {
//        doNothing().when(adminRepository).deleteById(1);
//        adminService.delete(1);
//        verify(adminRepository, times(1)).deleteById(1);
//    }
//
//    @Test
//    void testGetAll() {
//        when(adminRepository.findAll()).thenReturn(Arrays.asList(admin));
//        List<Admin> admins = adminService.getAll();
//        assertFalse(admins.isEmpty());
//        assertEquals(1, admins.size());
//    }
//}
//
