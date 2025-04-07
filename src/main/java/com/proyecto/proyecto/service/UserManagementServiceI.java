package com.proyecto.proyecto.service;

import java.time.LocalDateTime;
import java.util.List;
import com.proyecto.proyecto.dto.UserDTO;
import com.proyecto.proyecto.model.User;

/**
 * Interfaz del servicio de Usuario
 * 
 * @author Alejandro_Mirabent_Landero
 *
 */
public interface UserManagementServiceI {

	/**
	 * Crea un nuevo usuario
	 * 
	 * @param newUser
	 * @return boolean
	 */
	public boolean createUser(User newUser);

	/**
	 * Modifica un usuario
	 * 
	 * @param userDTO
	 * @param userSesion
	 * @return boolean
	 */
	public boolean updateUser(UserDTO userDTO, User userSesion);

	/**
	 * Modifica la contraseña de un usuario
	 * 
	 * @param password
	 * @param usuSesion
	 * @return
	 */
	public boolean updateUserPassword(String password, User usuSesion);

	/**
	 * Elimina un usuario
	 * 
	 * @param idUser
	 */
	public void deleteUser(Long idUser);

	/**
	 * Lista todos los usuarios
	 * 
	 * @return List<User>
	 */
	public List<User> searchAll();

	/**
	 * Busca un usuario por su id
	 * 
	 * @param id
	 * @return User
	 */
	public User searchById(Long id);

	/**
	 * Busca un usuario por su nombre
	 * 
	 * @param nameUser
	 * @return User
	 */
	public User searchByName(String nameUser);

	/**
	 * Busca un usuario por su email
	 * 
	 * @param email
	 * @return User
	 */
	public User searchByEmail(String email);

	/**
	 * Verifica si existe un usuario con ese nombre
	 * 
	 * @param nameUser
	 * @return boolean
	 */
	public boolean checkIfNameExists(String nameUser);

	/**
	 * Verifica si existe un usuario con ese email
	 * 
	 * @param email
	 * @return boolean
	 */
	public boolean checkIfEmailExists(String email);

	/**
	 * Lista usuarios filtrando por el nombre
	 * 
	 * @param filterString
	 * @return List<User>
	 */
	public List<User> searchByNameIgnoreCase(String filterString);

	/**
	 * Busca un usuario por su nombre y verifica que no tenga la cuenta suspendida
	 * en la fecha actual
	 * 
	 * @param username
	 * @param now
	 * @return User
	 */
	public User searchByUsernameAndNotBanned(String username, LocalDateTime now);

	/**
	 * Añade días a la suspensión
	 * 
	 * @param idUser
	 * @param daysToAdd
	 */
	public void addDaysToBannedTime(Long idUser, int daysToAdd);

}
