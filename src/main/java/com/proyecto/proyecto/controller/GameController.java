package com.proyecto.proyecto.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import com.proyecto.proyecto.dto.BoardGameDTO;
import com.proyecto.proyecto.dto.ReportDTO;
import com.proyecto.proyecto.model.BoardGame;
import com.proyecto.proyecto.model.Report;
import com.proyecto.proyecto.model.Review;
import com.proyecto.proyecto.service.GameManagementServiceI;
import com.proyecto.proyecto.service.ReviewManagementServiceI;
import com.proyecto.proyecto.service.UserManagementServiceI;
import com.proyecto.proyecto.storage.StorageService;

/**
 * Clase controlador de Juego de mesa
 * 
 * @author Alejandro_Mirabent_Landero
 *
 */
@Controller
public class GameController {

	@Autowired
	private GameManagementServiceI gameService;

	@Autowired
	private UserManagementServiceI userService;

	@Autowired
	private ReviewManagementServiceI reviewService;

	@Autowired
	private StorageService storageService;

	private BoardGame newModelGame = new BoardGame();

	/**
	 * Muestra la lista de juegos de mesa
	 * 
	 * @param model
	 * @param request
	 * @param filterString
	 * @return gameList
	 */
	@GetMapping("/gameList")
	public String listGames(Model model, HttpServletRequest request, @RequestParam(value = "q", required = false) String filterString) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User loggedInUser = (User) authentication.getPrincipal();
		com.proyecto.proyecto.model.User usuario = userService.searchByEmail(loggedInUser.getUsername());

		if (usuario != null) {
			request.getSession().setAttribute("userSession", usuario);
		}

		// Comprueba si el usuario está vetado
		LocalDateTime now = LocalDateTime.now();
		if (usuario != null && usuario.getBannedTime() != null && usuario.getBannedTime().isAfter(now)) {
			model.addAttribute("bannedMessage", "Cuenta suspendida. No puedes iniciar sesión.");
			return "forward:/login";
		}
		
		model.addAttribute("activePage", "gameList");

		// Si el parámetro de búsqueda es nulo, se muestra todos los juegos
		if (filterString == null) {
			model.addAttribute("gameList", gameService.searchAll());
		} else {
			model.addAttribute("gameList", gameService.searchByNameIgnoreCase(filterString));
		}

		return "boardGame/gameList";
	}

	/**
	 * Muestra la vista de un juego de mesa
	 * 
	 * @param id
	 * @param model
	 * @return gameView
	 */
	@GetMapping("/game/{idGame}")
	public String viewGame(@PathVariable("idGame") Long id, Model model) {
		BoardGame game = gameService.searchById(id);
		List<Review> reviews = reviewService.getReviewsForGame(id);

		// Añade el juego y una nueva valoración al modelo
		model.addAttribute("game", game);
		model.addAttribute("review", new Review());

		if (!reviews.isEmpty()) {
			// Si hay valoraciones, añade la lista de valoraciones al modelo
			model.addAttribute("listReview", reviews);
		}

		// Añade la lista de valoraciones al modelo incluso si está vacía
		model.addAttribute("listReview", reviews);
		return "boardGame/gameView";
	}

	/**
	 * Muestra la vista del formulario de denuncia
	 * 
	 * @param idGame
	 * @param model
	 * @return reportForm
	 */
	@GetMapping("/game/{idGame}/report")
	public String showReportForm(@PathVariable("idGame") Long idGame, Model model) {
		model.addAttribute("idGame", idGame);
		model.addAttribute("report", new Report());
		return "boardGame/reportForm";
	}

	/**
	 * Muestra la vista para crear un nuevo juego de mesa
	 * 
	 * @param model
	 * @return newGame
	 */
	@GetMapping("/newGame")
	public String nuevoJuego(Model model) {
		model.addAttribute("activePage", "newGame");
		model.addAttribute("newGame", newModelGame);
		return "boardGame/newGame";
	}

	/**
	 * Crea un nuevo juego de mesa
	 * 
	 * @param gameDTO
	 * @param file
	 * @return gameList
	 */
	@PostMapping("/newGame/submit")
	public String crearJuego(@ModelAttribute("newGame") BoardGameDTO gameDTO, @RequestParam("file") MultipartFile file) {

		// Valida el tipo de archivo
		if (!file.isEmpty()) {
			String contentType = file.getContentType();
			if (!contentType.equals("image/png") && !contentType.equals("image/jpeg")
					&& !contentType.equals("image/jpg")) {
				return "project/newProject";
			}
		}

		// Alamacena la imagen
		String img = storageService.storeGame(file);

		// Asigna los valores del DTO a los campos correspondientes de BoardGame
		BoardGame game = new BoardGame();
		game.setNameGame(gameDTO.getNameGame());
		game.setCategory(gameDTO.getCategory());
		game.setDescriptionGame(gameDTO.getDescriptionGame());

		game.setImageGame(MvcUriComponentsBuilder.fromMethodName(GameController.class, "serveFile", img).build().toUriString());

		if (gameService.createGame(game))
			return "redirect:/gameList";
		else {
			newModelGame = game;
			return "redirect:/newGame";
		}
	}

	/**
	 * Elimina un juego
	 * 
	 * @param idGame
	 * @return gameList
	 */
	@GetMapping("/deleteGame")
	public String deleteGame(@RequestParam("idGame") Long idGame) {
		gameService.deleteGame(idGame);
		return "redirect:/gameList";
	}

	/**
	 * Muestra la vista para modificar un juego de mesa
	 * 
	 * @param idGame
	 * @param model
	 * @return updateGame
	 */
	@GetMapping("/updateGame")
	public String updateGame(@RequestParam("idGame") Long idGame, Model model) {
		BoardGame boardGame = gameService.searchById(idGame);
		model.addAttribute("game", boardGame);
		return "boardGame/updateGame";
	}

	/**
	 * Modifica un juego de mesa
	 * 
	 * @param gameDTO
	 * @param model
	 * @param idGame
	 * @return gameList
	 */
	@PostMapping("/updateGame/submit")
	public String updateGameSubmit(@ModelAttribute("juego") BoardGameDTO gameDTO, Model model, @RequestParam("idGame") Long idGame) {
		BoardGame game = gameService.searchById(idGame);
		if (game != null) {

			// Modifica los campos permitidos del juego
			game.setNameGame(gameDTO.getNameGame());
			game.setDescriptionGame(gameDTO.getDescriptionGame());
			game.setCategory(gameDTO.getCategory());

			// Actualiza el juego en la base de datos
			if (gameService.updateGame(game, idGame))
				return "redirect:/gameList";
		}
		return "redirect:/updateGame";
	}

	/**
	 * Lista los juegos denunciados
	 * 
	 * @param model
	 * @param stringFilter
	 * @return reportedGames
	 */
	@GetMapping("/reportedGames")
	public String listReportedGame(Model model, @RequestParam(value = "q", required = false) String stringFilter) {

	    // Obtiene la lista de juegos denunciados
	    List<BoardGame> reportedGames = gameService.getReportedGames();

	    List<BoardGame> filteredGames = new ArrayList<>();
	    if (stringFilter != null && !stringFilter.isEmpty()) {
	        String filter = stringFilter.toLowerCase();

	        // Filtra los juegos denunciados según el filtro de búsqueda
	        for (BoardGame boardGame : reportedGames) {
	            if (boardGame.getNameGame().toLowerCase().contains(filter)) {
	                filteredGames.add(boardGame);
	            }
	        }
	    } else {
	        // Si no hay filtro, incluye todos los juegos denunciados
	        filteredGames.addAll(reportedGames);
	    }
	    model.addAttribute("reportedGames", filteredGames);

	    // Crea un Map para almacenar los comentarios y usuarios por ID de juego
	    Map<Long, List<ReportDTO>> reportsMap = new HashMap<>();
	    for (BoardGame game : filteredGames) {
	    	
	        // Obtiene las denuncias para el juego actual
	        List<Report> reports = game.getReportList();
	        
	        // Crea una lista de ReportDTO para almacenar las denuncias y usuarios
	        List<ReportDTO> reportDTOs = new ArrayList<>();
	        for (Report report : reports) {
	            // Crea un nuevo ReportDTO y establece el comentario, el usuario
	            ReportDTO reportDTO = new ReportDTO();
	            reportDTO.setComment(report.getComment());
	            reportDTO.setUser(report.getUser());
	            
	            // Añade el ReportDTO a la lista
	            reportDTOs.add(reportDTO);
	        }
	        
	        // Añade la lista de ReportDTO al map, utilizando el ID del juego como clave
	        reportsMap.put(game.getIdGame(), reportDTOs);
	    }
	    model.addAttribute("activePage", "reportedGames");
	    model.addAttribute("reportsMap", reportsMap);

	    return "boardGame/reportedGames";
	}

	/**
	 * Obtiene un archivo y lo envía como respuesta
	 * 
	 * @param filename
	 * @return estado 200
	 */
	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

		// Carga el archivo como un recurso
		Resource file = storageService.loadAsResource(filename);

		// Devuelve el archivo como respuesta con código de estado 200 (OK)
		return ResponseEntity.ok().body(file);
	}

}
