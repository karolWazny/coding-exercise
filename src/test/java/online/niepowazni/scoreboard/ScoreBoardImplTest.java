package online.niepowazni.scoreboard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ScoreBoardImplTest {

    private ScoreBoard scoreBoard;

    @BeforeEach
    public void prepareEmptyBoard() {
        scoreBoard = new ScoreBoardImpl();
    }

    @Test
    @DisplayName("Fresh score board returns empty list of games")
    public void freshScoreBoardIsEmpty() {
        List<Game> games = scoreBoard.getSummary();
        Assertions.assertNotNull(games);
    }

    @Test
    @DisplayName("Fresh score board is formatted as empty string")
    public void formatEmptyBoardTest() {
        Assertions.assertEquals("", scoreBoard.formatted());
    }

    @Test
    @DisplayName("New game can be added to score board")
    public void startGameTest() {
        scoreBoard.startGame("Poland", "Germany");
        List<Game> games = scoreBoard.getSummary();
        Assertions.assertEquals(1, games.size());
    }

    @Test
    @DisplayName("ScoreBoard returns games in summary")
    public void scoreBoardReturnsGames() {
        scoreBoard.startGame("Poland", "Germany");
        List<Game> games = scoreBoard.getSummary();
        Game game = games.get(0);
        Assertions.assertEquals("Poland", game.getHomeTeam());
        Assertions.assertEquals("Germany", game.getAwayTeam());
    }
}
