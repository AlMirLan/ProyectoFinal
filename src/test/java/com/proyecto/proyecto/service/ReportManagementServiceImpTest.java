package com.proyecto.proyecto.service;

import com.proyecto.proyecto.dto.ReportDTO;
import com.proyecto.proyecto.model.BoardGame;
import com.proyecto.proyecto.model.Report;
import com.proyecto.proyecto.model.User;
import com.proyecto.proyecto.repository.BoardGameRepositoryI;
import com.proyecto.proyecto.repository.ReportRepositoryI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ReportManagementServiceImpTest {

    @InjectMocks
    private ReportManagementServiceImp reportService;

    @Mock
    private ReportRepositoryI reportRepo;

    @Mock
    private BoardGameRepositoryI gameRepo;

    private BoardGame game;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        game = new BoardGame();
        game.setIdGame(1L);
        game.setReportCount(0);

        user = new User();
        user.setIdUser(1L);
    }

    @Test
    void testCreateReport() {
        Long gameId = 1L;
        String comment = "Contenido inapropiado";
        ReportDTO reportDTO = new ReportDTO();

        when(gameRepo.findById(gameId)).thenReturn(Optional.of(game));
        when(reportRepo.findByBoardGameIdGame(gameId)).thenReturn(new ArrayList<>());

        reportService.createReport(reportDTO, gameId, user, comment);

        ArgumentCaptor<Report> reportCaptor = ArgumentCaptor.forClass(Report.class);
        verify(reportRepo).save(reportCaptor.capture());
        Report savedReport = reportCaptor.getValue();

        assertEquals(comment, savedReport.getComment());
        assertEquals(user, savedReport.getUser());
        assertEquals(game, savedReport.getBoardGame());
        assertEquals(user.getIdUser(), savedReport.getIdReport().getUserId());
        assertEquals(game.getIdGame(), savedReport.getIdReport().getGameId());
    }

    @Test
    void testCreateReportThrowsExceptionWhenGameNotFound() {
        Long invalidGameId = 99L;
        when(gameRepo.findById(invalidGameId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            reportService.createReport(new ReportDTO(), invalidGameId, user, "Comentario");
        });
    }

    @Test
    void testUpdateReportCount() {
        List<Report> mockReports = Arrays.asList(new Report(), new Report());
        when(reportRepo.findByBoardGameIdGame(game.getIdGame())).thenReturn(mockReports);

        reportService.updateReportCount(game);

        assertEquals(2, game.getReportCount());
        verify(gameRepo).save(game);
    }

    @Test
    void testGetCommentsByGameId() {
        List<String> comments = Arrays.asList("Comentario 1", "Comentario 2");
        when(reportRepo.findCommentsByGameId(1L)).thenReturn(comments);

        List<String> result = reportService.getCommentsByGameId(1L);

        assertEquals(comments, result);
        verify(reportRepo).findCommentsByGameId(1L);
    }
}
