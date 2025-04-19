package com.proyecto.proyecto.dto;

import com.proyecto.proyecto.model.Category;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO de Juego de mesa
 * 
 * @author Alejandro_Mirabent_Landero
 *
 */
@Getter
@Setter
public class BoardGameDTO {

	/** Identificador de Juego de mesa **/
	private Long idGame;

	/** Nombre del juego **/
	private String nameGame;

	/** Decripcion del juego **/
	private String descriptionGame;

	/** Imagen del juego **/
	private String imageGame;

	/** PDF del juego **/
	private String pdfGame;

	/** Categoria del juego **/
	private Category category;

	/** Numero de valoraciones **/
	private int reviewCount;

	/** Nota media de valoraciones **/
	private double averageRating;

	/** Numero de denuncias **/
	private int reportCount;

}
