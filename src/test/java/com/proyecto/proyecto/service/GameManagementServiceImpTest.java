package com.proyecto.proyecto.service;

import com.proyecto.proyecto.model.BoardGame;
import com.proyecto.proyecto.model.Category;
import com.proyecto.proyecto.repository.BoardGameRepositoryI;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GameManagementServiceImpTest {

	@Mock
	private BoardGameRepositoryI gameRepo;

	@InjectMocks
	private GameManagementServiceImp service;

	@Test
	public void testSearchAll() {
		BoardGame game1 = new BoardGame();
		BoardGame game2 = new BoardGame();
		when(gameRepo.findAll()).thenReturn(Arrays.asList(game1, game2));

		List<BoardGame> result = service.searchAll();

		assertEquals(2, result.size());
		verify(gameRepo).findAll();
	}

	@Test
	public void testSearchByNameIgnoreCase() {
		String filter = "Too Many Bones";
		BoardGame game = new BoardGame();
		game.setNameGame("Too Many Bones");
		when(gameRepo.findByNameGameContainingIgnoreCase(filter)).thenReturn(Arrays.asList(game));

		List<BoardGame> result = service.searchByNameIgnoreCase(filter);

		assertEquals(1, result.size());
		assertEquals("Too Many Bones", result.get(0).getNameGame());
	}

	@Test
	public void testSearchByIdFound() {
		BoardGame game = new BoardGame();
		game.setIdGame(1L);
		when(gameRepo.findById(1L)).thenReturn(Optional.of(game));

		BoardGame result = service.searchById(1L);

		assertNotNull(result);
		assertEquals(1L, result.getIdGame());
	}

	@Test
	public void testSearchByIdNotFound() {
		when(gameRepo.findById(99L)).thenReturn(Optional.empty());

		BoardGame result = service.searchById(99L);

		assertNull(result);
	}

	@Test
	public void testCreateGame() {
		BoardGame game = new BoardGame();
		game.setNameGame("Too Many Bones");

		boolean result = service.createGame(game);

		assertTrue(result);
		assertEquals(0, game.getAverageRating());
		assertEquals(0, game.getReviewCount());
		verify(gameRepo).save(game);
	}

	@Test
	public void testUpdateGameExists() {
		Long id = 1L;
		BoardGame oldGame = new BoardGame();
		oldGame.setIdGame(id);
		oldGame.setImageGame("image.jpg");
		oldGame.setAverageRating(4);
		oldGame.setReviewCount(10);

		BoardGame updatedGame = new BoardGame();
		updatedGame.setNameGame("Burncycle");
		updatedGame.setDescriptionGame("Descripción");
		updatedGame.setCategory(Category.COOPERATIVO);

		when(gameRepo.findById(id)).thenReturn(Optional.of(oldGame));

		boolean result = service.updateGame(updatedGame, id);

		assertTrue(result);
		verify(gameRepo).save(oldGame);
		assertEquals("Burncycle", oldGame.getNameGame());
		assertEquals("Descripción", oldGame.getDescriptionGame());
		assertEquals(Category.COOPERATIVO, oldGame.getCategory());
		assertEquals("image.jpg", updatedGame.getImageGame());
	}

	@Test
	public void testUpdateGameNotExists() {
		Long id = 99L;
		BoardGame updatedGame = new BoardGame();
		when(gameRepo.findById(id)).thenReturn(Optional.empty());

		boolean result = service.updateGame(updatedGame, id);

		assertFalse(result);
		verify(gameRepo, never()).save(any());
	}

	@Test
	public void testDeleteGame() {
		BoardGame game = new BoardGame();
		game.setIdGame(1L);
		when(gameRepo.findById(1L)).thenReturn(Optional.of(game));

		service.deleteGame(1L);

		verify(gameRepo).delete(game);
	}

	@Test
	public void testGetReportedGamesWithReports() {
		BoardGame game1 = new BoardGame();
		game1.setReportCount(1);
		BoardGame game2 = new BoardGame();
		game2.setReportCount(0);
		when(gameRepo.findAll()).thenReturn(Arrays.asList(game1, game2));

		List<BoardGame> result = service.getReportedGames();

		assertEquals(1, result.size());
		assertEquals(1, result.get(0).getReportCount());
	}

	@Test
	public void testGetReportedGamesEmpty() {
		BoardGame game1 = new BoardGame();
		game1.setReportCount(0);
		when(gameRepo.findAll()).thenReturn(Arrays.asList(game1));

		List<BoardGame> result = service.getReportedGames();

		assertTrue(result.isEmpty());
	}
}
