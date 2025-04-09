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
	 * @param project Proyecto
	 * @param user Usuario
	 * @return boolean Devuelve verdadero o falso
	 */
	public boolean createProject(Project project, User user);

	/**
	 * Elimina un proyecto
	 * 
	 * @param idProject ID del proyecto
	 */
	public void deleteProject(Long idProject);

	/**
	 * Lista todos los proyectos
	 * 
	 * @return List<Project> Devuelve una lista de proyectos
	 */
	public List<Project> searchAll();

	/**
	 * Busca un proyecto por su id
	 * 
	 * @param id ID del proyecto
	 * @return Devuelve el proyecto
	 */
	public Project searchById(Long id);

	/**
	 * Busca proyectos filtrando por el nombre
	 * 
	 * @param filterString Filtro de cadena
	 * @return List<Project> Devuelve una lista de proyectos
	 */
	public List<Project> searchByNameProjectIgnoreCase(String filterString);

	/**
	 * Lista los proyectos pertenecientes al usuario
	 * 
	 * @param user Usuario
	 * @return List<Project> Devuelve listado de proyectos del usuario
	 */
	public List<Project> searchByMyProjects(User user);

	/**
	 * Busca proyectos pertenecientes a un usuario filtrandopor el nombre.
	 * 
	 * @param filterString Filtro de cadena
	 * @param user Usuario
	 * @return List<Project> Devuelve un lista de proyectos
	 */
	public List<Project> searchByMyProjectsIgnoreCase(String filterString, User user);

	/**
	 * Registra el voto de un usuario a un proyecto
	 * 
	 * @param vote Voto
	 * @param idProject ID del proyecto
	 * @param user Usuario
	 * @return boolean Devuelve verdadero o falso
	 */
	public boolean vote(boolean vote, Long idProject, User user);

	/**
	 * Calcula la aprovación de un proyecto
	 * 
	 * @param project Proyecto
	 * @return Devuelve el porcentaje de aprobación
	 */
	boolean calculateApproval(Project project);

	/**
	 * Evoluciona un proyecto a juego de mesa
	 * 
	 * @param project Proyecto
	 */
	void projectEvolve(Project project);

}
