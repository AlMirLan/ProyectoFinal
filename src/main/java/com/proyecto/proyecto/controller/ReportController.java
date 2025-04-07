package com.proyecto.proyecto.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.proyecto.proyecto.dto.ReportDTO;
import com.proyecto.proyecto.model.User;
import com.proyecto.proyecto.service.ReportManagementServiceI;

/**
 * Clase controlador de Denuncia
 * 
 * @author Alejandro_Mirabent_Landero
 *
 */
@Controller
public class ReportController {

	@Autowired
	private ReportManagementServiceI reportService;

	/**
	 * Crea una denuncia
	 * 
	 * @param idGame
	 * @param reportDTO
	 * @param request
	 * @return /game/" + idGame
	 */
	@PostMapping("/report")
	public String report(@RequestParam(name = "idGame") Long idGame, @ModelAttribute("report") ReportDTO reportDTO,
			HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("userSession");
		String comment = reportDTO.getComment();
		reportService.createReport(reportDTO, idGame, user, comment);

		return "redirect:/game/" + idGame;
	}

}
