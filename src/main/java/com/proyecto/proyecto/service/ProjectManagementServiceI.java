package com.proyecto.proyecto.service;

import java.util.List;
import com.proyecto.proyecto.model.Project;
import com.proyecto.proyecto.model.User;

/**
 * Interfaz del servicio de Proyecto
 * 
 * @author Alejandro_Mirabent_Landero
 *
 */
public interface ProjectManagementServiceI {

	/**
	 * Crea un nuevo proyecto
	 * 
	 * @param project
	 * @param user
	 * @return boolean
	 */
	public boolean createProject(Project project, User user);

	/**
	 * Elimina un proyecto
	 * 
	 * @param idProject
	 */
	public void deleteProject(Long idProject);

	/**
	 * Lista todos los proyectos
	 * 
	 * @return List<Project>
	 */
	public List<Project> searchAll();

	/**
	 * Busca un proyecto por su id
	 * 
	 * @param id
	 * @return
	 */
	public Project searchById(Long id);

	/**
	 * Busca proyectos filtrando por el nombre
	 * 
	 * @param filterString
	 * @return List<Project>
	 */
	public List<Project> searchByNameProjectIgnoreCase(String filterString);

	/**
	 * Lista los proyectos pertenecientes al usuario
	 * 
	 * @param user
	 * @return List<Project>
	 */
	public List<Project> searchByMyProjects(User user);

	/**
	 * Busca proyectos pertenecientes a un usuario filtrandopor el nombre.
	 * 
	 * @param filterString
	 * @param user
	 * @return List<Project>
	 */
	public List<Project> searchByMyProjectsIgnoreCase(String filterString, User user);

	/**
	 * Registra el voto de un usuario a un proyecto
	 * 
	 * @param vote
	 * @param idProject
	 * @param user
	 * @return boolean
	 */
	public boolean vote(boolean vote, Long idProject, User user);

	/**
	 * Calcula la aprovación de un proyecto
	 * 
	 * @param project
	 * @return
	 */
	boolean calculateApproval(Project project);

	/**
	 * Evoluciona un proyecto a juego de mesa
	 * 
	 * @param project
	 */
	void projectEvolve(Project project);

}
