package com.proyecto.proyecto.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.proyecto.proyecto.model.*;
import com.proyecto.proyecto.repository.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class ProjectManagementServiceImpTest {

    @InjectMocks
    private ProjectManagementServiceImp projectService;

    @Mock
    private ProjectRepositoryI projectRepo;

    @Mock
    private VoteRepositoryI voteRepo;

    @Mock
    private BoardGameRepositoryI gameRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchAll() {
        List<Project> mockList = Arrays.asList(new Project(), new Project());
        when(projectRepo.findAll()).thenReturn(mockList);

        List<Project> result = projectService.searchAll();

        assertEquals(2, result.size());
        verify(projectRepo).findAll();
    }

    @Test
    void testSearchById() {
        Project project = new Project();
        project.setIdProject(1L);
        when(projectRepo.findById(1L)).thenReturn(Optional.of(project));

        Project result = projectService.searchById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getIdProject());
    }

    @Test
    void testSearchByNameProjectIgnoreCase() {
        List<Project> mockList = Arrays.asList(new Project());
        when(projectRepo.findByNameProjectContainingIgnoreCase("Prueba")).thenReturn(mockList);

        List<Project> result = projectService.searchByNameProjectIgnoreCase("Prueba");

        assertEquals(1, result.size());
    }

    @Test
    void testCreateProject() {
        Project project = new Project();
        User user = new User();
        user.setIdUser(5L);

        boolean result = projectService.createProject(project, user);

        assertTrue(result);
        assertEquals(0, project.getApproval());
        assertEquals(0, project.getVotesCount());
        assertEquals(5L, project.getIdAuthor());
        verify(projectRepo).save(project);
    }

    @Test
    void testVote() {
        User user = new User();
        user.setIdUser(10L);

        Project project = new Project();
        project.setIdProject(2L);

        when(projectRepo.findById(2L)).thenReturn(Optional.of(project));
        when(voteRepo.findByProject(project)).thenReturn(Arrays.asList());

        boolean result = projectService.vote(true, 2L, user);

        assertFalse(result);
        verify(voteRepo).save(any(Vote.class));
    }

    @Test
    void testCalculateApproval_shouldEvolveProject() {
        Project project = new Project();
        project.setIdProject(3L);
        project.setCategory(Category.COOPERATIVO);
        project.setNameProject("Arydia");
        project.setDescriptionProject("Descripción");
        project.setImageProject("img");

        Vote v1 = new Vote(); v1.setPositive(true);
        Vote v2 = new Vote(); v2.setPositive(true);
        // Simulamos más de 10 votos positivos
        List<Vote> votes = Arrays.asList(v1, v1, v1, v1, v1, v1, v1, v1, v1, v1, v2);

        when(voteRepo.findByProject(project)).thenReturn(votes);

        boolean result = projectService.calculateApproval(project);

        assertTrue(result);
        verify(gameRepo).save(any(BoardGame.class));
        verify(projectRepo).deleteById(project.getIdProject());
    }
    @Test
    void testSearchByMyProjects() {
        User user = new User();
        user.setIdUser(7L);

        List<Project> mockList = Arrays.asList(new Project(), new Project());
        when(projectRepo.findByIdAuthor(7L)).thenReturn(mockList);

        List<Project> result = projectService.searchByMyProjects(user);

        assertEquals(2, result.size());
        verify(projectRepo).findByIdAuthor(7L);
    }

    @Test
    void testSearchByMyProjectsIgnoreCase_shouldMatchOne() {
        User user = new User();
        user.setIdUser(8L);

        Project p1 = new Project();
        p1.setNameProject("Burncycle");

        Project p2 = new Project();
        p2.setNameProject("Hoplomachus Victorum");

        when(projectRepo.findByIdAuthor(8L)).thenReturn(Arrays.asList(p1, p2));

        List<Project> result = projectService.searchByMyProjectsIgnoreCase("plo", user);

        assertEquals(1, result.size());
        assertEquals("Hoplomachus Victorum", result.get(0).getNameProject());
    }

    @Test
    void testDeleteProject() {
        Project project = new Project();
        project.setIdProject(9L);

        when(projectRepo.findById(9L)).thenReturn(Optional.of(project));

        projectService.deleteProject(9L);

        verify(projectRepo).delete(project);
    }

    @Test
    void testProjectEvolve_shouldCreateBoardGame() {
        Project project = new Project();
        project.setIdProject(11L);
        project.setCategory(Category.RPG);
        project.setNameProject("Ker Nethalas");
        project.setDescriptionProject("Librojuego con dados");
        project.setImageProject("img123");

        projectService.projectEvolve(project);

        ArgumentCaptor<BoardGame> captor = ArgumentCaptor.forClass(BoardGame.class);
        verify(gameRepo).save(captor.capture());
        verify(projectRepo).deleteById(11L);

        BoardGame createdGame = captor.getValue();
        assertEquals("Ker Nethalas", createdGame.getNameGame());
        assertEquals("Librojuego con dados", createdGame.getDescriptionGame());
        assertEquals(Category.RPG, createdGame.getCategory());
    }


}
