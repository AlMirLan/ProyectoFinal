package com.proyecto.proyecto.service;

import com.proyecto.proyecto.dto.UserDTO;
import com.proyecto.proyecto.model.User;
import com.proyecto.proyecto.repository.UserRepositoryI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserManagementServiceImpTest {

    @InjectMocks
    private UserManagementServiceImp userService;

    @Mock
    private UserRepositoryI userRepo;

    @Spy
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser_NewUser_Success() {
        User user = new User();
        user.setEmail("usuarioPrueba@ejemplo.com");
        user.setNameUser("UsuarioPrueba");
        user.setPass("1234");

        when(userRepo.findByEmail("usuarioPrueba@ejemplo.com")).thenReturn(null);
        when(userRepo.findByNameUser("UsuarioPrueba")).thenReturn(null);

        boolean result = userService.createUser(user);

        assertFalse(result);
        verify(userRepo).save(any(User.class));
    }

    @Test
    void testCreateUser_ExistingEmailOrName_ReturnsTrue() {
        User user = new User();
        user.setEmail("usuarioPrueba@ejemplo.com");
        user.setNameUser("UsuarioPrueba");

        when(userRepo.findByEmail("usuarioPrueba@ejemplo.com")).thenReturn(new User());

        boolean result = userService.createUser(user);

        assertTrue(result);
        verify(userRepo, never()).save(any());
    }

    @Test
    void testUpdateUser_ValidUpdate_Success() {
        UserDTO dto = new UserDTO();
        dto.setEmail("nuevoUsuarioPrueba@ejemplo.com");
        dto.setNameUser("NuevoUsuario");
        dto.setPass("nuevaContraseña");

        User sessionUser = new User();
        sessionUser.setEmail("viejoUsuario@ejemplo.com");
        sessionUser.setNameUser("ViejoNombre");

        when(userRepo.findByEmail("usuarioPrueba@ejemplo.com")).thenReturn(null);
        when(userRepo.findByNameUser("NuevoUsuario")).thenReturn(null);

        boolean result = userService.updateUser(dto, sessionUser);

        assertFalse(result);
        verify(userRepo).save(sessionUser);
    }

    @Test
    void testUpdateUser_DuplicateEmailOrName_ReturnsTrue() {
        UserDTO dto = new UserDTO();
        dto.setEmail("existing@example.com");
        dto.setNameUser("ExistingName");

        User sessionUser = new User();
        sessionUser.setEmail("current@example.com");
        sessionUser.setNameUser("CurrentName");

        User existingUser = new User();
        existingUser.setEmail("existing@example.com"); // Necesario para evitar el null

        when(userRepo.findByEmail("existing@example.com")).thenReturn(existingUser);

        boolean result = userService.updateUser(dto, sessionUser);

        assertTrue(result);
        verify(userRepo, never()).save(any());
    }


    @Test
    void testCheckIfNameExists_Exists() {
        when(userRepo.findByNameUser("NombrePrueba")).thenReturn(new User());

        assertTrue(userService.checkIfNameExists("NombrePrueba"));
    }

    @Test
    void testCheckIfNameExists_NotExists() {
        when(userRepo.findByNameUser("NombrePrueba")).thenReturn(null);

        assertFalse(userService.checkIfNameExists("NombrePrueba"));
    }

    @Test
    void testUpdateUserPassword() {
        User user = new User();
        user.setPass("contraseñaVieja");

        boolean result = userService.updateUserPassword("contraseñaNueva", user);

        assertTrue(passwordEncoder.matches("contraseñaNueva", user.getPass()));
        assertFalse(result);
        verify(userRepo).save(user);
    }

    @Test
    void testAddDaysToBannedTime_UserExists() {
        User user = new User();
        user.setIdUser(1L);

        when(userRepo.findById(1L)).thenReturn(Optional.of(user));

        userService.addDaysToBannedTime(1L, 3);

        assertNotNull(user.getBannedTime());
        verify(userRepo).save(user);
    }

    @Test
    void testAddDaysToBannedTime_UserNotExists() {
        when(userRepo.findById(99L)).thenReturn(Optional.empty());

        userService.addDaysToBannedTime(99L, 3);

        verify(userRepo, never()).save(any());
    }

    @Test
    void testSearchBannedUsers() {
        User banned = new User();
        banned.setBannedTime(LocalDateTime.now().plusDays(1));

        User notBanned = new User();
        notBanned.setBannedTime(LocalDateTime.now().minusDays(1));

        when(userRepo.findAll()).thenReturn(List.of(banned, notBanned));

        List<User> result = userService.searchBannedUsers();

        assertEquals(1, result.size());
        assertEquals(banned, result.get(0));
    }

    @Test
    void testSearchByUsernameAndNotBanned_UserValidAndBanned() {
        LocalDateTime now = LocalDateTime.now();
        User user = new User();
        user.setBannedTime(now.plusDays(1));

        when(userRepo.findByNameUser("NombreUsuario")).thenReturn(user);

        assertEquals(user, userService.searchByUsernameAndNotBanned("NombreUsuario", now));
    }

    @Test
    void testSearchByUsernameAndNotBanned_UserNotBanned() {
        LocalDateTime now = LocalDateTime.now();
        User user = new User();
        user.setBannedTime(now.minusDays(1));

        when(userRepo.findByNameUser("NombreUsuario")).thenReturn(user);

        assertNull(userService.searchByUsernameAndNotBanned("NombreUsuario", now));
    }

    @Test
    void testSearchAll() {
        List<User> users = List.of(new User());
        when(userRepo.findAll()).thenReturn(users);
        assertEquals(users, userService.searchAll());
    }

    @Test
    void testSearchById() {
        User user = new User();
        when(userRepo.findById(1L)).thenReturn(Optional.of(user));
        assertEquals(user, userService.searchById(1L));
    }

    @Test
    void testSearchByEmail() {
        User user = new User();
        when(userRepo.findByEmail("emailUsuario@prueba")).thenReturn(user);
        assertEquals(user, userService.searchByEmail("emailUsuario@prueba"));
    }

    @Test
    void testSearchByName() {
        User user = new User();
        when(userRepo.findByNameUser("NombreUsuario")).thenReturn(user);
        assertEquals(user, userService.searchByName("NombreUsuario"));
    }

    @Test
    void testDeleteUser() {
        User user = new User();
        when(userRepo.findById(1L)).thenReturn(Optional.of(user));
        userService.deleteUser(1L);
        verify(userRepo).delete(user);
    }
}
