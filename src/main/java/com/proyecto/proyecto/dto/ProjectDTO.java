package com.proyecto.proyecto.dto;

import com.proyecto.proyecto.model.Category;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO de Proyecto
 * 
 * @author Alejandro_Mirabent_Landero
 *
 */
@Getter
@Setter
public class ProjectDTO {

	/** Identificador de proyecto **/
	private Long idProject;

	/** Nombre del proyecto **/
	private String nameProject;

	/** Descripcion del proyecto **/
	private String descriptionProject;

	/** Imagen del proyecto **/
	private String imageProject;

	/** PDF del proyecto **/
	private String pdfProject;

	/** Categoria del proyecto **/
	private Category category;

	/** Porcentaje de aprovacion **/
	private double approval;

	/** Numero de votos **/
	private int votesCount;

	/** Creador del proyecto **/
	private Long idAuthor;

}
