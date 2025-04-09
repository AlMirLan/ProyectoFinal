package com.proyecto.proyecto.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyecto.proyecto.model.Project;

/**
 * Repositorio de Proyecto
 * 
 * @author Alejandro_Mirabent_Landero
 *
 */
@Repository
public interface ProjectRepositoryI extends JpaRepository<Project, Long> {

	/**
	 * Método que lista todos los proyectos
	 * 
	 */
	public List<Project> findAll();

	/**
	 * Método que busca un proyecto cuyo nombre contenga una cadena específica,
	 * ignorando mayúsculas y minúsculas
	 * 
	 * @param filterString Filtro de cadena
	 * @return List<Project> Lista de proyectos
	 */
	public List<Project> findByNameProjectContainingIgnoreCase(String filterString);

	/**
	 * Método que busca un proyecto por su autor
	 * 
	 * @param idAuthor ID del usuario autor del proyecto
	 * @return
	 */
	public List<Project> findByIdAuthor(Long idAuthor);

}
