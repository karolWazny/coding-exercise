package online.niepowazni.scoreboard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class GameTest {

    @Test
    @DisplayName("Fresh game should have 0 - 0 score")
    public void freshGameShouldBeZeroZero() {
        Game game = Game.builder()
                .homeTeam("Sweden")
                .awayTeam("Denmark")
                .build();
        Assertions.assertEquals(0, game.getHomeScore());
        Assertions.assertEquals(0, game.getAwayScore());
    }

    @ParameterizedTest
    @CsvSource({
            "Sweden,Denmark",
            "Poland,Germany"
    })
    @DisplayName("Game should be displayed in specific format")
    public void gameShouldBeFormattedForDisplay(String homeTeam, String awayTeam) {
        Game game = Game.builder()
                .homeTeam(homeTeam)
                .awayTeam(awayTeam)
                .build();
        Assertions.assertEquals("%s 0 - %s 0".formatted(homeTeam, awayTeam), game.formatted());
    }
}
