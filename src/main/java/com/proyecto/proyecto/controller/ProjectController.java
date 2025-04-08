package com.proyecto.proyecto.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
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

import com.proyecto.proyecto.dto.ProjectDTO;
import com.proyecto.proyecto.model.Project;
import com.proyecto.proyecto.model.User;
import com.proyecto.proyecto.service.ProjectManagementServiceI;
import com.proyecto.proyecto.storage.StorageService;
import io.swagger.v3.oas.annotations.Operation;

@Controller
public class ProjectController {

	@Autowired
	private ProjectManagementServiceI projectService;

	@Autowired
	private StorageService storageService;

	private Project newModelProject = new Project();

	/**
	 * Muestra la lista de proyectos
	 * 
	 * @param model
	 * @param filterString
	 * @return projectList
	 */
	@GetMapping("/projectList")
	public String listProjects(Model model, @RequestParam(value = "q", required = false) String filterString) {
		model.addAttribute("activePage", "projectList");
		
		// Si el parámetro de búsqueda es nulo, se muestra todos los proyectos
		if (filterString == null)
			model.addAttribute("projectList", projectService.searchAll());
		else
			model.addAttribute("projectList", projectService.searchByNameProjectIgnoreCase(filterString));

		return "project/projectList";
	}

	/**
	 * Muestra la vista de un proyecto
	 * 
	 * @param idProject
	 * @param model
	 * @return projectView
	 */
	@GetMapping("/project/{idProject}")
	public String viewProject(@PathVariable long idProject, Model model) {
		Project project = projectService.searchById(idProject);
		model.addAttribute("project", project);
		return "project/projectView";
	}

	/**
	 * Registra el voto de un usuario
	 * 
	 * @param vote
	 * @param idProject
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@PostMapping("/voteProject")
	public String vote(@RequestParam("vote") boolean vote, @RequestParam("idProject") Long idProject, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("userSession");
		if (projectService.vote(vote, idProject, user)) {
			return "redirect:/projectList";
		} else {
			return "redirect:/project/" + idProject;
		}
	}

	/**
	 * Muestra la vista para crear un nuevo proyecto
	 * 
	 * @param model
	 * @return newProject
	 */
	@GetMapping("/newProject")
	public String newProject(Model model) {
		model.addAttribute("activePage", "newProject");
		model.addAttribute("newProject", newModelProject);
		return "project/newProject";
	}

	/**
	 * Crea un nuevo proyecto
	 * 
	 * @param proyecto
	 * @param file
	 * @param request
	 * @param model
	 * @return projectList
	 */
	@PostMapping("/newProject/submit")
	@Operation(summary = "Crea un proyecto")
	public String createProject(@ModelAttribute("newProject") ProjectDTO projectDTO, @RequestParam("file") MultipartFile file, HttpServletRequest request, Model model) {
		User user = (User) request.getSession().getAttribute("userSession");

		// Valida el tipo de archivo
		if (!file.isEmpty()) {
			String contentType = file.getContentType();
			if (!contentType.equals("image/png") && !contentType.equals("image/jpeg") && !contentType.equals("image/jpg")) {
			    return "project/projectList";
			}
		}

		// Alamacena la imagen
		String img = storageService.storeProject(file);

		// Asigna los valores del DTO a los campos correspondientes de Project
		Project project = new Project();
		project.setNameProject(projectDTO.getNameProject());
		project.setDescriptionProject(projectDTO.getDescriptionProject());
		project.setCategory(projectDTO.getCategory());

		if (img != null) {
			
			// Asigna la ruta de la imagen
			project.setImageProject(MvcUriComponentsBuilder.fromMethodName(ProjectController.class, "serveFile", img).build().toUriString());
		}

		if (projectService.createProject(project, user))
			return "redirect:/projectList";
		else {
			newModelProject = project;
			return "redirect:/newProject";
		}
	}

	/**
	 * Lista los proyectos del usuario
	 * 
	 * @param model
	 * @param filterString
	 * @param request
	 * @return myProjects
	 */
	@GetMapping("/myProjects")
	public String listMyProjects(Model model, @RequestParam(value = "q", required = false) String filterString,	HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("userSession");
		
		model.addAttribute("activePage", "myProjects");
		
		// Si el parámetro de búsqueda es nulo, se muestra todos los proyectos del usuario
		if (filterString == null)
			model.addAttribute("listMyProjects", projectService.searchByMyProjects(user));
		else
			model.addAttribute("listMyProjects", projectService.searchByMyProjectsIgnoreCase(filterString, user));

		return "project/myProjects";
	}

	/**
	 * Elimina un proyecto
	 * 
	 * @param idProject
	 * @return projectList
	 */
	@GetMapping("/deleteProject")
	public String deleteProject(@RequestParam("idProject") Long idProject) {
		projectService.deleteProject(idProject);
		return "redirect:/projectList";
	}
	
	@GetMapping("/deleteMyProject")
	public String deleteMyProject(@RequestParam("idProject") Long idProject) {
		projectService.deleteProject(idProject);
		return "redirect:/projectList";
	}

	/**
	 * Obtiene un archivo y lo envía como respuesta.
	 * 
	 * @param filename
	 * @return estado 200
	 */
	@GetMapping("/files1/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

		// Carga el archivo como un recurso
		Resource file = storageService.loadAsResource(filename);

		// Devuelve el archivo como respuesta con código de estado 200 (OK)
		return ResponseEntity.ok().body(file);
	}

}
