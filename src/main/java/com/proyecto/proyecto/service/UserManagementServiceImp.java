package com.proyecto.proyecto.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.proyecto.dto.UserDTO;
import com.proyecto.proyecto.model.User;
import com.proyecto.proyecto.repository.UserRepositoryI;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Implementación del servicio de Usuario
 * 
 * @author Alejandro_Mirabent_Landero
 *
 */
@Service
public class UserManagementServiceImp implements UserManagementServiceI {

	/** Repositorio **/
	@Autowired
	private UserRepositoryI userRepo;

	/** Interfaz para encriptar la contraseña **/
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public List<User> searchAll() {
		return userRepo.findAll();
	}

	@Override
	public List<User> searchByNameIgnoreCase(String filterString) {
		return userRepo.findByNameUserContainingIgnoreCase(filterString);
	}

	@Override
	public User searchById(Long id) {
		return userRepo.findById(id).orElse(null);
	}

	@Override
	public User searchByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public User searchByName(String nameUser) {
		return userRepo.findByNameUser(nameUser);
	}

	@Override
	public boolean createUser(User newUser) {
		User existUserByEmail = userRepo.findByEmail(newUser.getEmail());
		User existUserByName = userRepo.findByNameUser(newUser.getNameUser());

		// Comprueba si existe un usuario con ese nombre o email
		if (existUserByEmail != null || existUserByName != null) {
			return true;
		}

		// Encripta la contraseña
		newUser.setPass(passwordEncoder.encode(newUser.getPass()));
		userRepo.save(newUser);
		return false;
	}

	@Override
	public boolean checkIfNameExists(String nameUser) {
		User user = userRepo.findByNameUser(nameUser);
		return user != null;
	}

	@Override
	public boolean checkIfEmailExists(String email) {
		User user = userRepo.findByEmail(email);
		return user != null;
	}

	@Override
	public boolean updateUser(UserDTO userDTO, User userSesion) {
		User existUserByEmail = userRepo.findByEmail(userDTO.getEmail());
		User existUserByName = userRepo.findByNameUser(userDTO.getNameUser());

		boolean emailUpdated = existUserByEmail == null || existUserByEmail.getEmail().equals(userSesion.getEmail());
		boolean nameUpdated = existUserByName == null || existUserByName.getNameUser().equals(userSesion.getNameUser());

		// Comprueba si el nombre e email ya están en uso
		if (emailUpdated && nameUpdated) {

			// Actualiza los valores del usuario existente con los valores de userDTO
			userSesion.setNameUser(userDTO.getNameUser());

			// Encripta la contraseña
			userSesion.setPass(passwordEncoder.encode(userDTO.getPass()));
			userSesion.setEmail(userDTO.getEmail());
			userRepo.save(userSesion);
			return false;

		} else {
			return true;
		}
	}

	@Override
	public boolean updateUserPassword(String password, User user) {
		
		// Actualiza la contraseña del usuario
		user.setPass(passwordEncoder.encode(password));
		userRepo.save(user);

		return false;
	}

	@Override
	public void deleteUser(Long idUser) {
		userRepo.delete(userRepo.findById(idUser).get());

	}

	@Override
	public User searchByUsernameAndNotBanned(String username, LocalDateTime currentDate) {
		User user = userRepo.findByNameUser(username);

		// Verifica si el usuario existe y no está vetado
		if (user != null && user.getBannedTime() != null && user.getBannedTime().isAfter(currentDate)) {
			return user;
		} else {
			return null;
		}
	}

	@Override
	public void addDaysToBannedTime(Long userId, int daysToAdd) {
		User user = userRepo.findById(userId).orElse(null);

		if (user != null) {

			// Agrega los días seleccionados a la fecha actual del atributo bannedTime
			LocalDateTime newBannedTime = LocalDateTime.now().plusDays(daysToAdd);
			user.setBannedTime(newBannedTime);
			userRepo.save(user);
		}
	}

}
