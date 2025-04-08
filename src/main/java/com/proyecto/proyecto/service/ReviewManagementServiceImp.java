package com.proyecto.proyecto.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.proyecto.model.BoardGame;
import com.proyecto.proyecto.model.Review;
import com.proyecto.proyecto.model.User;
import com.proyecto.proyecto.repository.BoardGameRepositoryI;
import com.proyecto.proyecto.repository.ReviewRepositoryI;

/**
 * Implementación del servicio de Valoración
 * 
 * @author Alejandro_Mirabent_Landero
 *
 */
@Service
public class ReviewManagementServiceImp implements ReviewManagementServiceI {

	/** Repositorios **/
	@Autowired
	private ReviewRepositoryI reviewRepo;

	@Autowired
	private BoardGameRepositoryI gameRepo;

	@Override
	public void newReview(Review review, Long idGame, User user) {

		// Obtiene el juego por su ID o lanza una excepción si no se encuentra
		BoardGame boardGame = gameRepo.findById(idGame)
				.orElseThrow(() -> new IllegalArgumentException("Juego no encontrado"));

		// Crea una nueva valoración con el usuario, el juego, la nota y el comentario
		Review newReview = new Review(user, boardGame, review.getRating(), review.getComment());

		reviewRepo.save(newReview);

		updateRating(boardGame);
	}

	public void updateRating(BoardGame game) {
		// Obtiene todas las valoraciones asociadas al juego
		List<Review> reviews = reviewRepo.findByBoardGameIdGame(game.getIdGame());

		// Obtiene el número de valoraciones
		int reviewCount = reviews.size();

		// Verifica si hay valoraciones para calcular la nota media
		if (reviewCount > 0) {
			double totalRating = 0;

			// Calcula la suma total de las notas
			for (Review review : reviews) {
				totalRating += review.getRating();
			}

			// Calcula la nota media y la redondea
			double averageRating = totalRating / reviewCount;
			averageRating = Math.round(averageRating * 100) / 100.0;

			game.setAverageRating(averageRating);
		} else {

			// Si no hay valoraciones, establece la nota media a 0
			game.setAverageRating(0);
		}

		game.setReviewCount(reviewCount);

		// Guardar el juego actualizado en el repositorio
		gameRepo.save(game);
	}

	@Override
	public List<Review> getReviewsForGame(Long gameId) {
		return reviewRepo.findByBoardGameIdGame(gameId);
	}
}
