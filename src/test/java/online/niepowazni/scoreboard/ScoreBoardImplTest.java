package online.niepowazni.scoreboard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ScoreBoardImplTest {

    @Test
    @DisplayName("Should create scoreboard")
    public void shouldCreateScoreboard() {
        ScoreBoard scoreBoard = new ScoreBoardImpl();
    }

    @Test
    @DisplayName("Fresh score board returns empty list of games")
    public void freshScoreBoardIsEmpty() {
        ScoreBoard scoreBoard = new ScoreBoardImpl();
        List<Object> games = scoreBoard.getSummary();
        Assertions.assertNotNull(games);
    }
}
