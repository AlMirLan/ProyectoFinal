package com.proyecto.proyecto.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase Usuario
 * 
 * @author Alejandro_Mirabent_Landero
 *
 */
@Getter
@Setter
@Entity
@Table(name = "USER")
public class User implements Serializable {

	/** Serial version **/
	private static final long serialVersionUID = 1L;

	/** Identificador (PK) **/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Long idUser;

	/** Nombre del usuario **/
	@Column(name = "USER_NAME", unique = true)
	private String nameUser;

	/** Correo del usuario **/
	@Column(name = "USER_EMAIL")
	private String email;

	/** Contraseña del usuario **/
	@Column(name = "USER_PASS")
	private String pass;

	/** Tiempo de suspensión de la cuenta **/
	@Column(name = "USER_BANNED_TIME")
	private LocalDateTime bannedTime;

	/** Lista de Valoraciones relacionadas **/
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Review> reviewList;

	/** Lista de Denuncias relacionadas **/
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Report> reportList;

	/** Lista de Votos relacionados **/
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Vote> votetList;
}
