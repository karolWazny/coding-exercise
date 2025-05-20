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
        List<GameDto> games = scoreBoard.getSummary();
        Assertions.assertNotNull(games);
        Assertions.assertEquals(0, games.size());
    }

    @Test
    @DisplayName("Fresh score board is formatted as empty string")
    public void formatEmptyBoardTest() {
        Assertions.assertEquals("", scoreBoard.formattedSummary());
    }

    @Test
    @DisplayName("New game can be added to score board")
    public void startGameTest() {
        scoreBoard.startGame("Poland", "Germany");
        List<GameDto> games = scoreBoard.getSummary();
        Assertions.assertEquals(1, games.size());
    }

    @Test
    @DisplayName("ScoreBoard returns started games in a summary")
    public void scoreBoardReturnsGames() {
        scoreBoard.startGame("Poland", "Germany");
        List<GameDto> games = scoreBoard.getSummary();
        GameDto game = games.get(0);
        Assertions.assertEquals("Poland", game.homeTeam());
        Assertions.assertEquals("Germany", game.awayTeam());
    }

    @Test
    @DisplayName("ScoreBoard does not return finished games in the summary")
    public void testStartAndFinishGame() {
        scoreBoard.startGame("Poland", "Germany");
        scoreBoard.finishGame("Poland", "Germany");
        Assertions.assertEquals("", scoreBoard.formattedSummary());
        Assertions.assertEquals(0, scoreBoard.getSummary().size());
    }
}
