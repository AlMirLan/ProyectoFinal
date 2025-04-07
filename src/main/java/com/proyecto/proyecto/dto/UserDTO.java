package com.proyecto.proyecto.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO de Usuario
 * 
 * @author Alejandro_Mirabent_Landero
 *
 */
@Getter
@Setter
public class UserDTO {

	/** Identificador de usuario **/
	private Long idUser;

	/** Nombre del usuario **/
	private String nameUser;

	/** Correo del usuario **/
	private String email;

	/** Contraseña del usuario **/
	private String pass;

	/** Tiempo de suspensión de la cuenta **/
	private LocalDateTime bannedTime;
}
