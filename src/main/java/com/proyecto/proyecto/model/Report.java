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
 * Clase Denuncia
 * 
 * @author Alejandro_Mirabent_Landero
 *
 */
@Getter
@Setter
@Entity
@Table(name = "REPORT")
public class Report implements Serializable {

	/** Serial version **/
	private static final long serialVersionUID = 1L;

	/** Indetificador (PK) **/
	@EmbeddedId
	private ReportPK idReport;

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

}
