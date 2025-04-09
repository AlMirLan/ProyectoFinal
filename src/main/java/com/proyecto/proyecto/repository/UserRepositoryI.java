package com.proyecto.proyecto.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyecto.proyecto.model.User;

/**
 * Repositorio de Usuario
 * 
 * @author Alejandro_Mirabent_Landero
 *
 */
@Repository
public interface UserRepositoryI extends JpaRepository<User, Long> {

	/**
	 * Método que lista todos los usuarios
	 * 
	 */
	public List<User> findAll();

	/**
	 * Método que busca un usuario por su email
	 * 
	 * @param email Mail del usuario
	 * @return User Usuario
	 */
	public User findByEmail(String email);

	/**
	 * Método que busca por el nombre de usuario
	 * 
	 * @param username Nombre del usuario
	 * @return User Usuario
	 */
	public User findByNameUser(String username);

	/**
	 * Método que busca un usuario cuyo nombre contenga una cadena específica,
	 * ignorando mayúsculas y minúsculas
	 * 
	 * @param filterString Filtro como cadena
	 * @return List<User> Lista de usuarios
	 */
	public List<User> findByNameUserContainingIgnoreCase(String filterString);

}
