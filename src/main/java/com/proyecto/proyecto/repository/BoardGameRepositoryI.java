package com.proyecto.proyecto.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyecto.proyecto.model.BoardGame;

/**
 * Repositorio de Juego de mesa
 * 
 * @author Alejandro_Mirabent_Landero
 *
 */
@Repository
public interface BoardGameRepositoryI extends JpaRepository<BoardGame, Long> {

	/**
	 * Método que lista todos los juegos de mesa
	 * 
	 */
	public List<BoardGame> findAll();

	/**
	 * Método que busca un juego por su nombre
	 * 
	 * @param stringFilter Filtro como cadena
	 * @return List<BoardGame> Lista de juegos
	 */
	public List<BoardGame> findByNameGameContainingIgnoreCase(String stringFilter);

}
