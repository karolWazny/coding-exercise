package online.niepowazni.scoreboard;

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


}
