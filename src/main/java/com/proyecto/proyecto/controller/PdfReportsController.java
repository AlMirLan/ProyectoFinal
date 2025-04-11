package com.proyecto.proyecto.controller;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import com.proyecto.proyecto.model.BoardGame;
import com.proyecto.proyecto.model.Project;
import com.proyecto.proyecto.model.User;
import com.proyecto.proyecto.service.GameManagementServiceImp;
import com.proyecto.proyecto.service.ProjectManagementServiceImp;
import com.proyecto.proyecto.service.UserManagementServiceImp;

/**
 * Clase controlador de los reportes en PDF
 * 
 * @author Alejandro_Mirabent_Landero
 *
 */
@Controller
@RequestMapping("/pdfReports")
public class PdfReportsController {

	@Autowired
	private GameManagementServiceImp boardGameService;

	@Autowired
	private ProjectManagementServiceImp projectService;

	@Autowired
	private UserManagementServiceImp userService;

	@GetMapping("")
	public String showPdfReport(Model model) {
		model.addAttribute("activePage", "pdfReports");
		return "pdfReports/pdfReports";
	}

	@PostMapping("")
	public void generarPdfReport(@RequestParam("report") String reportType, HttpServletResponse response)
			throws IOException {
		response.setContentType("application/pdf");
		String headerKey = "Content-Disposition";
		String headerValue = "";

		Document document = new Document(PageSize.A4);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			PdfWriter.getInstance(document, baos);
			document.open();

			// Título y logo
			Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
			Paragraph title = new Paragraph("", titleFont);

			// Generar el contenido del PDF según la opción seleccionada
			switch (reportType) {
			case "1":
				headerValue = "attachment; filename=todos-los-juegos.pdf";
				title.add("Reporte de Todos los Juegos");
				document.add(title);
				generateGamesReport(document);
				break;
			case "2":
				headerValue = "attachment; filename=todos-los-proyectos.pdf";
				title.add("Reporte de Todos los Proyectos");
				document.add(title);
				generateProjectsReport(document);
				break;
			case "3":
				headerValue = "attachment; filename=todos-los-usuarios.pdf";
				title.add("Reporte de Todos los Usuarios");
				document.add(title);
				generateUsersReport(document);
				break;
			case "4":
				headerValue = "attachment; filename=usuarios-baneados.pdf";
				title.add("Reporte de Usuarios Baneados");
				document.add(title);
				generateBannedUsersReport(document);
				break;
			}

			document.close();

			response.setHeader(headerKey, headerValue);
			response.getOutputStream().write(baos.toByteArray());
			response.getOutputStream().flush();

		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	// Métodos para generar los diferentes tipos de reportes
	private void generateGamesReport(Document document) throws DocumentException {
		
		// Creamos la tabla con 6 columnas
		PdfPTable table = new PdfPTable(6);

		// Le damos todo el ancho posible
		table.setWidthPercentage(100);
		float[] columnWidths = { 0.9f, 2f, 3f, 1.6f, 2f, 1.6f };
		table.setWidths(columnWidths);

		// Añadimos un poco de espacio entre el título y la tabla
		table.setSpacingBefore(10f);

		// Damos algo de estilo a las celdas del encabezado
		PdfPCell headerCell = new PdfPCell();
		headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		headerCell.setPadding(5);
		headerCell.setBackgroundColor(new Color(211, 211, 211));

		// Añadimos cabeceras con estilo
		String[] headers = { "ID", "Nombre", "Categoría", "Nº Reseñas", "Valoración Media", "Nº Reportes" };
		for (String header : headers) {
			headerCell.setPhrase(new Phrase(header, FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
			table.addCell(headerCell);
		}

		// Añadimos estilo a las celdas de datos
		PdfPCell dataCell = new PdfPCell();
		dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		dataCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		dataCell.setPadding(5);

		// Datos
		List<BoardGame> games = boardGameService.searchAll();
		for (BoardGame game : games) {
			
			// ID
			dataCell.setPhrase(new Phrase(String.valueOf(game.getIdGame())));
			table.addCell(dataCell);

			// Nombre
			dataCell.setPhrase(new Phrase(game.getNameGame()));
			table.addCell(dataCell);

			// Categoría
			dataCell.setPhrase(new Phrase(game.getCategory().toString()));
			table.addCell(dataCell);

			// Nº Reseñas
			dataCell.setPhrase(new Phrase(String.valueOf(game.getReviewCount())));
			table.addCell(dataCell);

			// Valoración Media
			dataCell.setPhrase(new Phrase(String.valueOf(game.getAverageRating())));
			table.addCell(dataCell);

			// Nº Reportes
			dataCell.setPhrase(new Phrase(String.valueOf(game.getReportCount())));
			table.addCell(dataCell);
		}

		document.add(table);
	}

	private void generateProjectsReport(Document document) throws DocumentException {

		// Creamos la tabla con 5 columnas
		PdfPTable table = new PdfPTable(5);

		// Le damos todo el ancho posible
		table.setWidthPercentage(100);
		float[] columnWidths = { 0.9f, 3f, 3f, 1.6f, 2f};
		table.setWidths(columnWidths);
		
		// Añadimos un poco de espacio entre el título y la tabla
		table.setSpacingBefore(10f);

		// Damos algo de estilo a las celdas del encabezado
		PdfPCell headerCell = new PdfPCell();
		headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		headerCell.setPadding(5);
		headerCell.setBackgroundColor(new Color(211, 211, 211));

		// Añadimos cabeceras con estilo
		String[] headers = { "ID", "Nombre", "Categoría", "Aprobación", "Nº Votos" };
		for (String header : headers) {
			headerCell.setPhrase(new Phrase(header, FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
			table.addCell(headerCell);
		}

		// Añadimos estilo a las celdas de datos
		PdfPCell dataCell = new PdfPCell();
		dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		dataCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		dataCell.setPadding(5);

		// Datos
		List<Project> projects = projectService.searchAll();
		for (Project project : projects) {

			// ID
			dataCell.setPhrase(new Phrase(String.valueOf(project.getIdProject())));
			table.addCell(dataCell);

			// Nombre
			dataCell.setPhrase(new Phrase(String.valueOf(project.getNameProject())));
			table.addCell(dataCell);

			// Categoría
			dataCell.setPhrase(new Phrase(String.valueOf(project.getCategory().toString())));
			table.addCell(dataCell);

			// % de aprobación
			dataCell.setPhrase(new Phrase(String.valueOf(project.getApproval())));
			table.addCell(dataCell);

			// Votos
			dataCell.setPhrase(new Phrase(String.valueOf(project.getVotesCount())));
			table.addCell(dataCell);
		}

		document.add(table);
	}

	private void generateUsersReport(Document document) throws DocumentException {

		// Creamos la tabla con 4 columnas
		PdfPTable table = new PdfPTable(4);

		// Le damos todo el ancho posible
		table.setWidthPercentage(100);
		float[] columnWidths = { 0.9f, 3f, 3f, 1.6f};
		table.setWidths(columnWidths);
		
		// Añadimos un poco de espacio entre el título y la tabla
		table.setSpacingBefore(10f);

		// Damos algo de estilo a las celdas del encabezado
		PdfPCell headerCell = new PdfPCell();
		headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		headerCell.setPadding(5);
		headerCell.setBackgroundColor(new Color(211, 211, 211));

		// Añadimos cabeceras con estilo
		String[] headers = { "ID", "Nombre de Usuario", "Email", "Nº de Proyectos" };
		for (String header : headers) {
			headerCell.setPhrase(new Phrase(header, FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
			table.addCell(headerCell);
		}

		// Añadimos estilo a las celdas de datos
		PdfPCell dataCell = new PdfPCell();
		dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		dataCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		dataCell.setPadding(5);

		// Datos
		List<com.proyecto.proyecto.model.User> users = userService.searchAll();
		for (com.proyecto.proyecto.model.User user : users) {

			// Número de proyectos del usuario
			int countProjects = projectService.searchByMyProjects(user).size();

			// ID
			dataCell.setPhrase(new Phrase(String.valueOf(user.getIdUser())));
			table.addCell(dataCell);

			// Nombre
			dataCell.setPhrase(new Phrase(user.getNameUser()));
			table.addCell(dataCell);

			// Email
			dataCell.setPhrase(new Phrase(user.getEmail()));
			table.addCell(dataCell);

			// Número de proyectos
			dataCell.setPhrase(new Phrase(String.valueOf(countProjects)));
			table.addCell(dataCell);
		}

		document.add(table);
	}

	private void generateBannedUsersReport(Document document) throws DocumentException {

		// Consultamos los usuarios baneados
		List<User> bannedUsers = userService.searchBannedUsers();

		// Comprobamos los usuarios baneados, si hay o no, cambiamos la tabla del
		// reporte
		if (bannedUsers.isEmpty()) {

			// Creamos la tabla con 1 columna
			PdfPTable table = new PdfPTable(1);

			// Le damos todo el ancho posible
			table.setWidthPercentage(100);
			
			// Añadimos un poco de espacio entre el título y la tabla
			table.setSpacingBefore(10f);

			// Damos algo de estilo a las celdas del encabezado
			PdfPCell headerCell = new PdfPCell(new Phrase("Usuarios", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
			headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			headerCell.setPadding(5);
			headerCell.setBackgroundColor(new Color(211, 211, 211));
			table.addCell(headerCell);

			// Añadimos cabeceras con estilo y mandamos un mensaje indicando que no hay
			// usuarios baneados
			PdfPCell noUserCell = new PdfPCell(new Phrase("No se han encontrado usuarios baneados"));
			noUserCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			noUserCell.setPadding(5);
			table.addCell(noUserCell);

			document.add(table);
		} else {
			
			// Creamos la tabla con 4 columnas
			PdfPTable table = new PdfPTable(4);

			// Le damos todo el ancho posible
			table.setWidthPercentage(100);
			float[] columnWidths = { 0.9f, 2f, 2.5f, 2.5f};
			table.setWidths(columnWidths);
			
			// Añadimos un poco de espacio entre el título y la tabla
			table.setSpacingBefore(10f);

			// Damos algo de estilo a las celdas del encabezado
			PdfPCell headerCell = new PdfPCell();
			headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			headerCell.setPadding(5);
			headerCell.setBackgroundColor(new Color(211, 211, 211));

			// Añadimos cabeceras con estilo
			String[] headers = { "ID", "Nombre de Usuario", "Email", "Fecha fin de Baneo" };
			for (String header : headers) {
				headerCell.setPhrase(new Phrase(header, FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
				table.addCell(headerCell);
			}

			// Añadimos estilo a las celdas de datos
			PdfPCell dataCell = new PdfPCell();
			dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			dataCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			dataCell.setPadding(5);

			// DATOS
			for (com.proyecto.proyecto.model.User user : bannedUsers) {

				// ID
				dataCell.setPhrase(new Phrase(String.valueOf(user.getIdUser())));
				table.addCell(dataCell);

				// Nombre
				dataCell.setPhrase(new Phrase(user.getNameUser()));
				table.addCell(dataCell);

				// Email
				dataCell.setPhrase(new Phrase(user.getEmail()));
				table.addCell(dataCell);

				// Tiempo hasta fin de ban
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss 'del día' dd/MM/yyyy");
				String fecha = user.getBannedTime().format(formatter);
				dataCell.setPhrase(new Phrase(fecha));
				table.addCell(dataCell);
			}

			document.add(table);
		}
	}
}