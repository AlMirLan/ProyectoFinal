package com.proyecto.proyecto.model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase para el ID compuesto de Valoración
 * 
 * @author Alejandro_Mirabent_Landero
 *
 */
@Getter
@Setter
@Embeddable
public class ReviewPK implements Serializable {

	/** Serial version **/
	private static final long serialVersionUID = 1L;

	/** Identificador del usuario **/
	private Long userId;

	/** Identificador del juego de mesa **/
	private Long gameId;

	/**
	 * Constructor vacío
	 */
	public ReviewPK() {
		super();

	}

	/**
	 * Constructor
	 * 
	 * @param userId
	 * @param gameId
	 */
	public ReviewPK(Long userId, Long gameId) {
		this.userId = userId;
		this.gameId = gameId;
	}

}
