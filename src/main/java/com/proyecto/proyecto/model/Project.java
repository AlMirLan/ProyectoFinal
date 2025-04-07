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
 * Clase Proyecto
 * 
 * @author Alejandro_Mirabent_Landero
 *
 */
@Getter
@Setter
@Entity
@Table(name = "PROJECT")
public class Project implements Serializable {

	/** Serial version **/
	private static final long serialVersionUID = 1L;

	/** Identificador (PK) **/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PROJECT_ID")
	private Long idProject;

	/** Nombre del proyecto **/
	@Column(name = "PROJECT_NAME")
	private String nameProject;

	/** Descripcion del proyecto **/
	@Column(name = "PROJECT_DESCRIPTION")
	private String descriptionProject;

	/** Imagen del proyecto **/
	@Column(name = "PROJECT_IMAGE")
	private String imageProject;

	/** Categoria del proyecto **/
	@Column(name = "PROJECT_CATEGORY")
	@Enumerated(EnumType.STRING)
	private Category category;

	/** Porcentaje de aprovacion **/
	@Column(name = "APPROVAL")
	private double approval;

	/** Numero de votos **/
	@Column(name = "VOTES_COUNT")
	private int votesCount;

	/** Creador del proyecto **/
	@Column(name = "AUTHOR_ID")
	private Long idAuthor;

	/** Lista de Votos relacionados **/
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
	private List<Vote> voteList;

}
