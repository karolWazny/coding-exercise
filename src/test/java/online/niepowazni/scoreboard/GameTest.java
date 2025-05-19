package online.niepowazni.scoreboard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameTest {

    private Game game;

    @BeforeEach
    public void prepareGame() {
        game = Game.builder()
                .homeTeam("Sweden")
                .awayTeam("Denmark")
                .build();
    }

    @Test
    @DisplayName("Fresh game should have 0 - 0 score")
    public void freshGameShouldBeZeroZero() {
        Assertions.assertEquals(0, game.getHomeTeamScore());
        Assertions.assertEquals(0, game.getAwayTeamScore());
    }

    @Test
    @DisplayName("Game should be displayed in specific format")
    public void gameShouldBeFormattedForDisplay() {
        Assertions.assertEquals("Sweden 0 - Denmark 0", game.formatted());
    }

}
