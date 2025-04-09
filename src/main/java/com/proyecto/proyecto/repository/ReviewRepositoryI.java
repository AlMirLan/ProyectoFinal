package com.proyecto.proyecto.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyecto.proyecto.model.Review;
import com.proyecto.proyecto.model.ReviewPK;

/**
 * Repositorio de Valoración
 * 
 * @author Alejandro_Mirabent_Landero
 *
 */
@Repository
public interface ReviewRepositoryI extends JpaRepository<Review, ReviewPK> {

	/**
	 * Método que lista todas las valoraciones
	 * 
	 */
	public List<Review> findAll();

	/**
	 * Método que busca las valoraciones que tiene un juego de mesa
	 * 
	 * @param gameId ID del juego
	 * @return List<Review> Lista de reseñas/reviews
	 */
	public List<Review> findByBoardGameIdGame(Long gameId);

}
