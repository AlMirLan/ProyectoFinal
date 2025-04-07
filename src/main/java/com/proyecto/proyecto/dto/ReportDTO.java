package com.proyecto.proyecto.dto;

import com.proyecto.proyecto.model.BoardGame;
import com.proyecto.proyecto.model.ReportPK;
import com.proyecto.proyecto.model.User;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO de Denuncia
 * 
 * @author Alejandro_Mirabent_Landero
 *
 */
@Getter
@Setter
public class ReportDTO {

	/** Indetificador de denuncia **/
	private ReportPK idReport;

	/** Comentario **/
	private String comment;

	/** Relación con el ID de Usuario **/
	private User user;

	/** Relación con el ID de Juego de mesa **/
	private BoardGame boardGame;

}
