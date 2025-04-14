package com.proyecto.proyecto.service;

import com.proyecto.proyecto.model.BoardGame;
import com.proyecto.proyecto.model.Review;
import com.proyecto.proyecto.model.User;
import com.proyecto.proyecto.repository.BoardGameRepositoryI;
import com.proyecto.proyecto.repository.ReviewRepositoryI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ReviewManagementServiceImpTest {

    @InjectMocks
    private ReviewManagementServiceImp reviewService;

    @Mock
    private ReviewRepositoryI reviewRepo;

    @Mock
    private BoardGameRepositoryI gameRepo;

    private BoardGame game;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        game = new BoardGame();
        game.setIdGame(1L);

        user = new User();
        user.setIdUser(1L);
    }

    @Test
    void testNewReviewWithComment() {
        Review review = new Review();
        review.setRating(4);
        review.setComment("Muy buen juego");

        when(gameRepo.findById(1L)).thenReturn(Optional.of(game));
        when(reviewRepo.findByBoardGameIdGame(1L)).thenReturn(List.of(review));

        reviewService.newReview(review, 1L, user);

        ArgumentCaptor<Review> reviewCaptor = ArgumentCaptor.forClass(Review.class);
        verify(reviewRepo).save(reviewCaptor.capture());

        Review savedReview = reviewCaptor.getValue();
        assertEquals("Muy buen juego", savedReview.getComment());
        assertEquals(4, savedReview.getRating());
        assertEquals(game, savedReview.getBoardGame());
        assertEquals(user, savedReview.getUser());

        verify(gameRepo).save(game);
    }

    @Test
    void testNewReviewWithEmptyComment() {
        Review review = new Review();
        review.setRating(3);
        review.setComment("");

        when(gameRepo.findById(1L)).thenReturn(Optional.of(game));
        when(reviewRepo.findByBoardGameIdGame(1L)).thenReturn(List.of(review));

        reviewService.newReview(review, 1L, user);

        ArgumentCaptor<Review> reviewCaptor = ArgumentCaptor.forClass(Review.class);
        verify(reviewRepo).save(reviewCaptor.capture());

        Review savedReview = reviewCaptor.getValue();
        assertEquals("No se ha dejado ningún comentario", savedReview.getComment());
    }

    @Test
    void testNewReviewThrowsExceptionWhenGameNotFound() {
        Review review = new Review();
        when(gameRepo.findById(99L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            reviewService.newReview(review, 99L, user);
        });
    }

    @Test
    void testUpdateRatingWithReviews() {
        Review r1 = new Review();
        r1.setRating(3);

        Review r2 = new Review();
        r2.setRating(5);

        List<Review> reviews = Arrays.asList(r1, r2);

        when(reviewRepo.findByBoardGameIdGame(game.getIdGame())).thenReturn(reviews);

        reviewService.updateRating(game);

        assertEquals(4.0, game.getAverageRating());
        assertEquals(2, game.getReviewCount());
        verify(gameRepo).save(game);
    }

    @Test
    void testUpdateRatingWithNoReviews() {
        when(reviewRepo.findByBoardGameIdGame(game.getIdGame())).thenReturn(Collections.emptyList());

        reviewService.updateRating(game);

        assertEquals(0, game.getAverageRating());
        assertEquals(0, game.getReviewCount());
        verify(gameRepo).save(game);
    }

    @Test
    void testGetReviewsForGame() {
        List<Review> reviews = Arrays.asList(new Review(), new Review());
        when(reviewRepo.findByBoardGameIdGame(1L)).thenReturn(reviews);

        List<Review> result = reviewService.getReviewsForGame(1L);

        assertEquals(reviews, result);
        verify(reviewRepo).findByBoardGameIdGame(1L);
    }
}
