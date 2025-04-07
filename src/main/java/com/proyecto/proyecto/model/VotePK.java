package com.proyecto.proyecto.model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase para le ID compuesto de Voto
 * 
 * @author Alejandro_Mirabent_Landero
 *
 */
@Getter
@Setter
@Embeddable
public class VotePK implements Serializable {

	/** Serial version **/
	private static final long serialVersionUID = 1L;

	/** Identificador del usuario **/
	private Long userId;
	
	/** Identificador del proyecto **/
	private Long projectId;

}