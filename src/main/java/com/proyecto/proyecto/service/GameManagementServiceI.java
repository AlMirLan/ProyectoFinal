package com.proyecto.proyecto.service;

import java.util.List;
import com.proyecto.proyecto.model.BoardGame;

/**
 * Interfaz del servicio de Juego de mesa
 * 
 * @author Alejandro_Mirabent_Landero
 *
 */
public interface GameManagementServiceI {

	/**
	 * Crea un nuevo juego de mesa
	 * 
	 * @param game
	 * @return boolean
	 */
	public boolean createGame(BoardGame game);

	/**
	 * Modifica un juego de mesa
	 * 
	 * @param game
	 * @param idGame
	 * @return boolean
	 */
	public boolean updateGame(BoardGame game, Long idGame);

	/**
	 * Elimina un juego de mesa
	 * 
	 * @param idGame
	 */
	public void deleteGame(Long idGame);

	/**
	 * Lista todos los juegos
	 * 
	 * @return List<BoardGame>
	 */
	public List<BoardGame> searchAll();

	/**
	 * Busca un juego por su id
	 * 
	 * @param id
	 * @return BoardGame
	 */
	public BoardGame searchById(Long id);

	/**
	 * Lista juegos filtrando por el nombre
	 * 
	 * @param stringFilter
	 * @return List<BoardGame>
	 */
	public List<BoardGame> searchByNameIgnoreCase(String stringFilter);

	/**
	 * Lista los juegos denunciados
	 * 
	 * @return List<BoardGame>
	 */
	public List<BoardGame> getReportedGames();

}
