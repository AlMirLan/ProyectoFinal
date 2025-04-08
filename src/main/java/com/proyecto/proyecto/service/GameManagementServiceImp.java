package com.proyecto.proyecto.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.proyecto.model.BoardGame;
import com.proyecto.proyecto.repository.BoardGameRepositoryI;

/**
 * Implementacion del servicio de Juego de mesa
 * 
 * @author Alejandro_Mirabent_Landero
 *
 */
@Service
public class GameManagementServiceImp implements GameManagementServiceI {

	/** Repositorio **/
	@Autowired
	private BoardGameRepositoryI gameRepo;

	@Override
	public List<BoardGame> searchAll() {
		return gameRepo.findAll();
	}

	@Override
	public List<BoardGame> searchByNameIgnoreCase(String stringFilter) {
		return gameRepo.findByNameGameContainingIgnoreCase(stringFilter);
	}

	@Override
	public BoardGame searchById(Long id) {
		return gameRepo.findById(id).orElse(null);
	}

	@Override
	public boolean createGame(BoardGame game) {
		game.setAverageRating(0);
		game.setReviewCount(0);
		gameRepo.save(game);
		return true;
	}

	@Override
	public boolean updateGame(BoardGame boardGame, Long idGame) {
		try {
			BoardGame existingGame = gameRepo.findById(idGame).orElse(null);

			if (existingGame != null) {
				existingGame.setNameGame(boardGame.getNameGame());
				existingGame.setDescriptionGame(boardGame.getDescriptionGame());
				existingGame.setCategory(boardGame.getCategory());

				// Mantiene los valores de imagen, nota media y número de valoraciones
				boardGame.setImageGame(existingGame.getImageGame());
				boardGame.setAverageRating(existingGame.getAverageRating());
				boardGame.setReviewCount(existingGame.getReviewCount());

				gameRepo.save(existingGame);
				return true;
			} else {
				
				// El juego no existe en la base de datos
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void deleteGame(Long idGame) {
		gameRepo.delete(gameRepo.findById(idGame).get());
	}

	@Override
	public List<BoardGame> getReportedGames() {
		List<BoardGame> reportedGames = new ArrayList<>();
		List<BoardGame> allGames = gameRepo.findAll();

		for (BoardGame game : allGames) {
			if (game.getReportCount() > 0) {
				reportedGames.add(game);
			}
		}

		return reportedGames;
	}

}
