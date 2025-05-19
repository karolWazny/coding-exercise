package online.niepowazni.scoreboard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameTest {
    @Test
    @DisplayName("Should instantiate game")
    public void shouldInstantiateGame() {
        Game game = Game.builder()
                .homeTeam("SWE")
                .awayTeam("DEN")
                .build();
    }

    @Test
    @DisplayName("Fresh game should have 0 - 0 score")
    public void freshGameShouldBeZeroZero() {
        Game game = Game.builder()
                .homeTeam("SWE")
                .awayTeam("DEN")
                .build();
        Assertions.assertEquals(0, game.getHomeTeamScore());
        Assertions.assertEquals(0, game.getAwayTeamScore());
    }


}
