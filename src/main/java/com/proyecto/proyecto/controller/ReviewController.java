package com.proyecto.proyecto.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.proyecto.proyecto.dto.ReviewDTO;
import com.proyecto.proyecto.model.Review;
import com.proyecto.proyecto.model.User;
import com.proyecto.proyecto.service.ReviewManagementServiceI;

/**
 * Clase controlador de Valoración
 * 
 * @author Alejandro_Mirabent_Landero
 *
 */
@Controller
public class ReviewController {

	@Autowired
	ReviewManagementServiceI reviewService;

	/**
	 * Crea una nueva valoración
	 * 
	 * @param idGame
	 * @param reviewDTO
	 * @param request
	 * @param response
	 * @return /game/" + idGame
	 */
	@PostMapping("/value")
	public String value(@RequestParam(name = "idGame") Long idGame, @ModelAttribute("review") ReviewDTO reviewDTO,
			HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("userSession");
		Review review = convertToReview(reviewDTO);
		reviewService.newReview(review, idGame, user);
		return "redirect:/game/" + idGame;
	}

	/**
	 * Convierte un objeto ReviewDTO en Review
	 * 
	 * @param reviewDTO
	 * @return review
	 */
	private Review convertToReview(ReviewDTO reviewDTO) {
		Review review = new Review();
		review.setRating(reviewDTO.getRating());
		review.setComment(reviewDTO.getComment());
		return review;
	}

}
