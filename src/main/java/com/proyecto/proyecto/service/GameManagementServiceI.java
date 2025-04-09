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
	 * @param game Juego
	 * @return boolean Devuelve verdadero o falso
	 */
	public boolean createGame(BoardGame game);

	/**
	 * Modifica un juego de mesa
	 * 
	 * @param game Juego
	 * @param idGame ID del juego
	 * @return boolean Devuelve verdadero o falso
	 */
	public boolean updateGame(BoardGame game, Long idGame);

	/**
	 * Elimina un juego de mesa
	 * 
	 * @param idGame ID del juego
	 */
	public void deleteGame(Long idGame);

	/**
	 * Lista todos los juegos
	 * 
	 * @return List<BoardGame> Devuelve una lista de juegos
	 */
	public List<BoardGame> searchAll();

	/**
	 * Busca un juego por su id
	 * 
	 * @param id ID del juego
	 * @return BoardGame Devuelve el juego
	 */
	public BoardGame searchById(Long id);

	/**
	 * Lista juegos filtrando por el nombre
	 * 
	 * @param stringFilter Filtro como cadena
	 * @return List<BoardGame> Devuelve una lista de juegos
	 */
	public List<BoardGame> searchByNameIgnoreCase(String stringFilter);

	/**
	 * Lista los juegos denunciados
	 * 
	 * @return List<BoardGame> Devuelve una lista de juegos
	 */
	public List<BoardGame> getReportedGames();

}
