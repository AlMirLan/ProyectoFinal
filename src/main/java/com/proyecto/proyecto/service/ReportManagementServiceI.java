package com.proyecto.proyecto.service;

import java.util.List;
import com.proyecto.proyecto.dto.ReportDTO;
import com.proyecto.proyecto.model.BoardGame;
import com.proyecto.proyecto.model.User;

/**
 * Interfaz del servicio de Denuncia
 * 
 * @author Alejandro_Mirabent_Landero
 *
 */
public interface ReportManagementServiceI {

	/**
	 * Crea una denuncia
	 * 
	 * @param reportDTO
	 * @param idGame
	 * @param user
	 * @param comment
	 */
	public void createReport(ReportDTO reportDTO, Long idGame, User user, String comment);

	/**
	 * Actualiza el número de denuncias de un juego
	 * 
	 * @param game
	 */
	public void updateReportCount(BoardGame game);

	/**
	 * Lista los comentarios asociados a un juego mediante su ID.
	 * 
	 * @param gameId
	 * @return List<String>
	 */
	public List<String> getCommentsByGameId(Long gameId);


}
