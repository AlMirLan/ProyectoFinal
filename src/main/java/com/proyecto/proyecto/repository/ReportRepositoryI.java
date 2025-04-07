package com.proyecto.proyecto.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.proyecto.proyecto.model.Report;
import com.proyecto.proyecto.model.ReportPK;

/**
 * Repositorio de Denuncia
 * 
 * @author Alejandro_Mirabent_Landero
 *
 */
@Repository
public interface ReportRepositoryI extends JpaRepository<Report, ReportPK> {

	/**
	 * Método que lista las valoraciones asociadas a un juego de mesa
	 * 
	 * @param idGame
	 * @return List<Report>
	 */
	public List<Report> findByBoardGameIdGame(Long idGame);

	/**
	 * Método que lista todos los comentarios de las denuncias asociadas a un juego específico.
	 * 
	 * @param gameId
	 * @return List<String>
	 */
	@Query("SELECT r.comment FROM Report r WHERE r.boardGame.idGame = :gameId")
	public List<String> findCommentsByGameId(@Param("gameId") Long gameId);

}
