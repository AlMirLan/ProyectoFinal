package com.proyecto.proyecto.service;

import java.util.List;
import com.proyecto.proyecto.model.BoardGame;
import com.proyecto.proyecto.model.Review;
import com.proyecto.proyecto.model.User;

/**
 * Interfaz del servicio de Valoración
 * 
 * @author Alejandro_Mirabent_Landero
 *
 */
public interface ReviewManagementServiceI {

	/**
	 * Crea una una valoración
	 * 
	 * @param review Reseña/Review
	 * @param idJuego Id del juego
	 * @param user Usuario
	 */
	public void newReview(Review review, Long idGame, User user);

	/**
	 * Actualiza la nota media y el número de comentarios del juego
	 * 
	 * @param game Juego
	 */
	public void updateRating(BoardGame game);

	/**
	 * Lista todas las valoraciones asociadas a un juego
	 * 
	 * @param gameId ID de juego
	 * @return List<Review> Devuelve una lista de reseñas/reviews
	 */
	public List<Review> getReviewsForGame(Long gameId);

}
