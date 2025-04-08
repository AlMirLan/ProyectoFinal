package com.proyecto.proyecto.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase Juego de mesa
 * 
 * @author Alejandro_Mirabent_Landero
 *
 */
@Getter
@Setter
@Entity
@Table(name = "BOARD_GAME")
public class BoardGame implements Serializable {

	/** Serial version **/
	private static final long serialVersionUID = 1L;

	/** Identificador (PK) **/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GAME_ID")
	private Long idGame;

	/** Nombre del juego **/
	@Column(name = "GAME_NAME")
	private String nameGame;

	/** Decripcion del juego **/
	@Column(name = "GAME_DESCRIPTION", length = 1200)
	private String descriptionGame;

	/** Imagen del juego **/
	@Column(name = "GAME_IMAGE")
	private String imageGame;

	/** Categoria del juego **/
	@Column(name = "GAME_CATEGORY")
	@Enumerated(EnumType.STRING)
	private Category category;

	/** Numero de valoraciones **/
	@Column(name = "REWIEW_COUNT")
	private int reviewCount;

	/** Nota media de valoraciones **/
	@Column(name = "AVERAGE_RATING")
	private double averageRating;

	/** Numero de denuncias **/
	@Column(name = "REPORT_COUNT")
	private int reportCount;

	/** Lista de Valoraciones relacionados **/
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "boardGame")
	private List<Review> reviewList;

	/** Lista de Denuncias relacionadas **/
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "boardGame")
	private List<Report> reportList;

	/**
	 * Constructor por defecto
	 * 
	 */
	public BoardGame() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param category
	 * @param nameGame
	 * @param descriptionGame
	 * @param imageGame
	 * @param reviewCount
	 * @param averageRating
	 */
	public BoardGame(Category category, String nameGame, String descriptionGame, String imageGame, int reviewCount,
			double averageRating) {
		super();
		this.category = category;
		this.nameGame = nameGame;
		this.descriptionGame = descriptionGame;
		this.imageGame = imageGame;
		this.reviewCount = reviewCount;
		this.averageRating = averageRating;
	}

}
