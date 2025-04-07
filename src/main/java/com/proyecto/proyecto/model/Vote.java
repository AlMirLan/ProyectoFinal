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
 * Clase Voto
 * 
 * @author Alejandro_Mirabent_Landero
 *
 */
@Getter
@Setter
@Entity
@Table(name = "VOTE")
public class Vote implements Serializable {

	/** Serial version **/
	private static final long serialVersionUID = 1L;

	/** Identificador (PK) **/
	@EmbeddedId
	private VotePK idVote;

	/** Voto positivo o negativo **/
	@Column(name = "POSITE")
	private boolean positive;

	/** Relación con el ID de Usuario */
	@ManyToOne
	@MapsId("userId")
	@JoinColumn(name = "user_id")
	private User user;

	/** Relación con el ID de Proyecto **/
	@ManyToOne
	@MapsId("projectId")
	@JoinColumn(name = "project_id")
	private Project project;

}
