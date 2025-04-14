package com.proyecto.proyecto;

import com.proyecto.proyecto.model.User;
import com.proyecto.proyecto.repository.UserRepositoryI;
import com.proyecto.proyecto.service.UserManagementServiceImp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class IntegracionTest {

    @Autowired
    private UserManagementServiceImp userService;

    @Autowired
    private UserRepositoryI userRepo;

    @Test
    @Transactional
    void testCreateUser_Integration() {
    	
        // Datos de entrada para el usuario
        User newUser = new User();
        newUser.setEmail("usuarioTest@ejemplo.com");
        newUser.setNameUser("Usuario Test");
        newUser.setPass("contraseñaTest");

        // Crear el usuario usando el servicio
        boolean result = userService.createUser(newUser);

        // Verificar que el usuario no exista antes de la creación
        assertThat(result).isFalse();

        // Verificar que el usuario se ha guardado en la base de datos
        User savedUser = userRepo.findByEmail("usuarioTest@ejemplo.com");
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getNameUser()).isEqualTo("Usuario Test");
        assertThat(savedUser.getEmail()).isEqualTo("usuarioTest@ejemplo.com");
    }
}
