package online.niepowazni.scoreboard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @ParameterizedTest
    @CsvSource({
            "Sweden,Denmark",
            "Poland,Germany"
    })
    @DisplayName("Game should be displayed in specific format")
    public void gameShouldBeFormattedForDisplay(String homeTeam, String awayTeam) {
        game = Game.builder()
                .homeTeam(homeTeam)
                .awayTeam(awayTeam)
                .build();
        Assertions.assertEquals("%s 0 - %s 0".formatted(homeTeam, awayTeam), game.formatted());
    }
}
