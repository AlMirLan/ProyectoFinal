package com.proyecto.proyecto.dto;

import com.proyecto.proyecto.model.BoardGame;
import com.proyecto.proyecto.model.ReviewPK;
import com.proyecto.proyecto.model.User;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO de Valoración
 * 
 * @author Alejandro_Mirabent_Landero
 *
 */
@Getter
@Setter
public class ReviewDTO {
	
	/** Identificador de valoración **/
	private ReviewPK idReview;

	/** Nota */
	private int rating;

	/** Comentario **/
	private String comment;

	/** Relación con el ID de Usuario **/
	private User user;

	/** Relación con el ID de Juego de mesa **/
	private BoardGame boardGame;

}
