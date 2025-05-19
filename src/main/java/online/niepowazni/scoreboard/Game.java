package online.niepowazni.scoreboard;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Game {

    final String homeTeam;
    final String awayTeam;

    public int getHomeTeamScore() {
        return 0;
    }

    public int getAwayTeamScore() {
        return 0;
    }
}
