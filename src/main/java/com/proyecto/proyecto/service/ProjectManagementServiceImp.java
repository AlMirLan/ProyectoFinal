package com.proyecto.proyecto.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.proyecto.model.BoardGame;
import com.proyecto.proyecto.model.Project;
import com.proyecto.proyecto.model.User;
import com.proyecto.proyecto.model.Vote;
import com.proyecto.proyecto.model.VotePK;
import com.proyecto.proyecto.repository.BoardGameRepositoryI;
import com.proyecto.proyecto.repository.ProjectRepositoryI;
import com.proyecto.proyecto.repository.VoteRepositoryI;

/**
 * Implementación del servicio de Proyecto
 * 
 * @author Alejandro_Mirabent_Landero
 *
 */
@Service
public class ProjectManagementServiceImp implements ProjectManagementServiceI {

	/** Repositorios **/
	@Autowired
	private ProjectRepositoryI projectRepo;

	@Autowired
	private VoteRepositoryI voteRepo;

	@Autowired
	private BoardGameRepositoryI gameRepo;

	@Override
	public List<Project> searchAll() {
		return projectRepo.findAll();
	}

	@Override
	public Project searchById(Long idProject) {
		return projectRepo.findById(idProject).orElse(null);
	}

	@Override
	public List<Project> searchByNameProjectIgnoreCase(String filterString) {
		return projectRepo.findByNameProjectContainingIgnoreCase(filterString);
	}

	@Override
	public List<Project> searchByMyProjects(User user) {
		return projectRepo.findByIdAuthor(user.getIdUser());
	}

	@Override
	public List<Project> searchByMyProjectsIgnoreCase(String filterString, User user) {

		List<Project> projectsList = projectRepo.findByIdAuthor(user.getIdUser());
		List<Project> filteredProjects = new ArrayList<>();

		// Itera por la lista de proyectos
		for (Project project : projectsList) {

			// Si el nombre contiene la cadena de busqueda se añade a la nueva lista
			if (project.getNameProject().contains(filterString)) {
				filteredProjects.add(project);
			}
		}

		return filteredProjects;
	}

	@Override
	public boolean createProject(Project project, User user) {
		project.setApproval(0);
		project.setVotesCount(0);
		project.setIdAuthor(user.getIdUser());
		projectRepo.save(project);
		return true;
	}

	@Override
	public void deleteProject(Long idProject) {
		projectRepo.delete(projectRepo.findById(idProject).get());

	}

	@Override
	public boolean vote(boolean v, Long idProject, User user) {
		Project project = projectRepo.findById(idProject).orElse(null);

		Vote vote = new Vote();
		VotePK votePK = new VotePK();

		vote.setPositive(v);

		// Se establecen los IDs del usuario y del proyecto en votePK.
		votePK.setUserId(user.getIdUser());
		votePK.setProjectId(project.getIdProject());

		// Se establece el id de Vote
		vote.setIdVote(votePK);

		// Se establecen el usuario y el proyecto
		vote.setUser(user);
		vote.setProject(project);

		voteRepo.save(vote);

		return calculateApproval(project);
	}

	@Override
	public boolean calculateApproval(Project project) {

		// Obtiene la lista de votos del proyecto
		List<Vote> votes = voteRepo.findByProject(project);

		int positiveVotes = 0;
		double percentage;

		// Cuenta el número de votos positivos
		for (int i = 0; i < votes.size(); i++) {
			if (votes.get(i).isPositive()) {
				positiveVotes++;
			}
		}

		// Calcula el porcentaje de aprobación en base a los votos positivos y el total
		// de votos
		percentage = (positiveVotes * 100.0) / votes.size();

		// Redondea el porcentaje a dos decimales
		percentage = Math.round(percentage * 100.0) / 100.0;

		// Actualiza el porcentaje de aprobación y el número de votos
		project.setApproval(percentage);
		project.setVotesCount(votes.size());

		projectRepo.save(project);

		// Si el porcentaje de aprobación es mayor al 90% y hay al menos 10 votos, el
		// proyecto evoluciona a juego
		if (percentage > 90 && votes.size() >= 10) {
			projectEvolve(project);
			return true;

			// Si el porcentaje de aprobación es menor al 50% y hay más de 10 votos, o el
			// porcentaje es menor al 20% y hay más de 3 votos, el proyecto es eliminado
		} else if ((percentage < 50 && votes.size() > 10) || (percentage < 20 && votes.size() > 3)) {
			projectRepo.deleteById(project.getIdProject());
			return true;
		}

		return false;
	}

	@Override
	public void projectEvolve(Project project) {
		BoardGame boardGame = new BoardGame(project.getCategory(), project.getNameProject(),
				project.getDescriptionProject(), project.getImageProject(), 0, 0);
		gameRepo.save(boardGame);
		projectRepo.deleteById(project.getIdProject());
	}

}
