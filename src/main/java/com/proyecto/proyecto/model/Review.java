package com.proyecto.proyecto.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase Valoracion
 * 
 * @author Alejandro_Mirabent_Landero
 *
 */
@Getter
@Setter
@Entity
@Table(name = "REVIEW")
public class Review implements Serializable {

	/** Serial version **/
	private static final long serialVersionUID = 1L;

	/** Identificador (PK) **/
	@EmbeddedId
	private ReviewPK idReview;

	/** Nota */
	@Column(name = "RATING")
	private int rating;

	/** Comentario **/
	@Column(name = "COMMENT")
	private String comment;

	/** Relación con el ID de Usuario **/
	@ManyToOne
	@MapsId("userId")
	@JoinColumn(name = "user_id")
	private User user;

	/** Relación con el ID de Juego de mesa **/
	@ManyToOne
	@MapsId("gameId")
	@JoinColumn(name = "game_id")
	private BoardGame boardGame;

	/**
	 * Constructor vacío
	 */
	public Review() {
		super();

	}

	/**
	 * Constructor
	 * 
	 * @param user
	 * @param boardGame
	 * @param rating
	 * @param comment
	 */
	public Review(User user, BoardGame boardGame, int rating, String comment) {
		this.user = user;
		this.boardGame = boardGame;
		this.rating = rating;
		this.comment = comment;
		this.idReview = new ReviewPK(user.getIdUser(), boardGame.getIdGame());
	}

}
