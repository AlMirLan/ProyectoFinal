package com.proyecto.proyecto.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.proyecto.dto.ReportDTO;
import com.proyecto.proyecto.model.BoardGame;
import com.proyecto.proyecto.model.Report;
import com.proyecto.proyecto.model.ReportPK;
import com.proyecto.proyecto.model.User;
import com.proyecto.proyecto.repository.BoardGameRepositoryI;
import com.proyecto.proyecto.repository.ReportRepositoryI;

/**
 * Implementación del servicio de Denuncia
 * 
 * @author Alejandro_Mirabent_Landero
 *
 */
@Service
public class ReportManagementServiceImp implements ReportManagementServiceI {

	/** Repositorios **/
	@Autowired
	private ReportRepositoryI reportRepo;

	@Autowired
	private BoardGameRepositoryI gameRepo;

	@Override
	public void createReport(ReportDTO reportDTO, Long idGame, User user, String comment) {

		// Obtiene el juego por su ID o lanza una excepción si no se encuentra
		BoardGame boardGame = gameRepo.findById(idGame)
				.orElseThrow(() -> new IllegalArgumentException("Juego no encontrado"));

		Report newReport = new Report();
		ReportPK reportPK = new ReportPK();

		newReport.setComment(comment);

		// Se establecen los IDs del usuario y del juego en reportPK.
		reportPK.setUserId(user.getIdUser());
		reportPK.setGameId(boardGame.getIdGame());

		// Se establece el id de Report
		newReport.setIdReport(reportPK);

		// Se establecen el usuario y el juego
		newReport.setUser(user);
		newReport.setBoardGame(boardGame);

		reportRepo.save(newReport);

		updateReportCount(boardGame);
	}

	@Override
	public void updateReportCount(BoardGame game) {
		List<Report> reports = reportRepo.findByBoardGameIdGame(game.getIdGame());

		game.setReportCount(reports.size());
		gameRepo.save(game);
	}

	@Override
	public List<String> getCommentsByGameId(Long gameId) {
		return reportRepo.findCommentsByGameId(gameId);
	}

}
