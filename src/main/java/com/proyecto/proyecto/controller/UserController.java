package com.proyecto.proyecto.controller;

import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.proyecto.proyecto.dto.UserDTO;
import com.proyecto.proyecto.model.User;
import com.proyecto.proyecto.service.UserManagementServiceI;
import io.swagger.v3.oas.annotations.Operation;

/**
 * Clase controlador de Usuario
 * 
 * @author Alejandro_Mirabent_Landero
 *
 */
@Controller
public class UserController {

	@Autowired
	private UserManagementServiceI userService;

	/**
	 * Procesa el inicio de sesión
	 * 
	 * @param user
	 * @param request
	 * @return gameList
	 */
	@PostMapping("/auth/login-post")
	public String processLogin(@ModelAttribute("user") UserDTO userDTO, HttpServletRequest request) {
		String username = userDTO.getNameUser();
		User loginUser = userService.searchByUsernameAndNotBanned(username, LocalDateTime.now());

		// Si el usuario está vetado se muestra un mensaje y redirige al login
		if (loginUser == null) {
			request.getSession().setAttribute("bannedMessage", "Cuenta suspendida. No puedes iniciar sesión hasta: ");
			return "redirect:/login";
		} else {

			// Si el inicio de sesión es exitoso, se guarda el usuario en la sesión y se
			// redirige a la lista de juegos
			request.getSession().setAttribute("userSession", loginUser);
			return "redirect:/gameList";
		}
	}

	/**
	 * Obtiene una lista de usuarios
	 * 
	 * @param model
	 * @param filterString
	 * @return usersList
	 */
	@GetMapping("/usersList")
	public String listUsers(Model model, @RequestParam(value = "q", required = false) String filterString) {
		model.addAttribute("activePage", "usersList");

		// Si el parámetro de búsqueda es nulo, se muestra todos los usuarios
		if (filterString == null) {
			model.addAttribute("listOfUsers", userService.searchAll());
		} else {
			model.addAttribute("listOfUsers", userService.searchByNameIgnoreCase(filterString));
		}

		return "user/usersList";
	}

	/**
	 * Crea un nuevo objeto User y lo añade al modelo para su uso en la vista del
	 * formulario de registro.
	 * 
	 * @param model
	 * @return register
	 */
	@GetMapping("/register")
	public String newUser(Model model) {
		model.addAttribute("newUser", new User());
		return "user/register";
	}

	/**
	 * Crea un nuevo usuario a partir del formulario de registro y redirige al
	 * inicio de sesión
	 * 
	 * @param userDTO
	 * @param model
	 * @return
	 */
	@PostMapping("/register/submit")
	@Operation(summary = "Crea un usuario y te redirige")
	public String createNewUser(@ModelAttribute("newUser") UserDTO userDTO, Model model) {

		// Valida si la contraseña es nula o vacía
		if (userDTO.getPass() == null || userDTO.getPass().isEmpty()) {
			model.addAttribute("errorPass", "La contraseña no puede estar vacía.");
			return "user/register";
		}

		// Se crea un Usuario y se le asigna los valores de userDTO
		User user = new User();
		user.setNameUser(userDTO.getNameUser());
		user.setPass(userDTO.getPass());
		user.setEmail(userDTO.getEmail());

		// Verifica si existe un usuario con ese nombre y/o email
		boolean errorNombre = userService.checkIfNameExists(user.getNameUser());
		boolean errorCorreo = userService.checkIfEmailExists(user.getEmail());

		if (errorNombre) {
			model.addAttribute("errorName", "El nombre de usuario ya está registrado.");
		}

		if (errorCorreo) {
			model.addAttribute("errorEmail", "El correo electrónico ya está registrado.");
		}

		if (errorNombre || errorCorreo) {
			return "user/register";
		}

		userService.createUser(user);
		return "redirect:/login";
	}

	/**
	 * Muestra la página del perfil del usuario
	 * 
	 * @param model
	 * @return profile
	 */
	@GetMapping("/user/profile")
	public String userProfile(Model model) {
		User user = new User();
		model.addAttribute("modifiedUser", user);
		return "user/profile";
	}

	/**
	 * Modifica un usuario y cierra sesión
	 * 
	 * @param userDTO
	 * @param request
	 * @param model
	 * @return
	 */
	@PostMapping("/user/updateUser")
	public String updateUser(@ModelAttribute("modifiedUser") UserDTO userDTO, HttpServletRequest request, Model model) {

		// Obtiene el usuario de la sesión
		User usuSesion = (User) request.getSession().getAttribute("userSession");

		// Establece el id del usuario
		userDTO.setIdUser(usuSesion.getIdUser());

		boolean errorName = false;
		boolean errorEmail = false;

		// Verifica si el nombre ya está registrado, excepto si es el nombre actual del
		// usuario
		if (!userDTO.getNameUser().equals(usuSesion.getNameUser())) {
			errorName = userService.checkIfNameExists(userDTO.getNameUser());
		}

		// Verifica si el email ya está registrado, excepto si es el email actual del
		// usuario
		if (!userDTO.getEmail().equals(usuSesion.getEmail())) {
			errorEmail = userService.checkIfEmailExists(userDTO.getEmail());
		}

		if (errorName) {
			model.addAttribute("errorName", "El nombre de usuario ya está registrado.");
			return "user/profile";
		}

		if (errorEmail) {
			model.addAttribute("errorEmail", "El correo electrónico ya está registrado.");
			return "user/profile";
		}

		// Actualiza el usuario
		boolean updated = userService.updateUser(userDTO, usuSesion);

		if (!updated) {
			return "redirect:/auth/logout";
		} else {
			return "redirect:/user/profile?errorCorreo=true";
		}
	}

	/**
	 * Modifica la contraseña de un usuario y cierra sesión
	 * 
	 * @param password
	 * @param request
	 * @param model
	 * @return /auth/logout
	 */
	@PostMapping("/user/updateUserPassword")
	public String updateAdminUserPassword(@RequestParam("password") String password, HttpServletRequest request,
			Model model) {

		// Obtiene el usuario de la sesión
		User usuSesion = (User) request.getSession().getAttribute("userSession");

		// Actualiza la contraseña del usuario
		boolean updated = userService.updateUserPassword(password, usuSesion);

		if (!updated) {
			return "redirect:/auth/logout";
		} else {
			return "redirect:/user/profile?passwordUpdated=true";
		}
	}

	/**
	 * Elimina un usuario
	 * 
	 * @param idUser
	 * @return usersList
	 */
	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam("idUser") Long idUser) {
		userService.deleteUser(idUser);
		return "redirect:/usersList";
	}

	/**
	 * Añade días a la duración de la suspensión de la cuenta de un usuario
	 * 
	 * @param idUser
	 * @param daysToAdd
	 * @return usersList
	 */
	@PostMapping("/addDaysToBannedTime")
	public String addDaysToBannedTime(@RequestParam("idUser") Long idUser,
			@RequestParam("daysToAdd") Integer daysToAdd) {
		userService.addDaysToBannedTime(idUser, daysToAdd);
		return "redirect:/usersList";
	}

}
