package com.proyecto.proyecto.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.proyecto.model.Project;
import com.proyecto.proyecto.model.Vote;
import com.proyecto.proyecto.model.VotePK;

/**
 * Repositorio de Voto
 * 
 * @author Alejandro_Mirabent_Landero
 *
 */
@Repository
public interface VoteRepositoryI extends JpaRepository<Vote, VotePK> {

	/**
	 * Método que lista los votos asociados a un proyecto
	 * 
	 * @param project
	 * @return List<Vote>
	 */
	public List<Vote> findByProject(Project project);
}
