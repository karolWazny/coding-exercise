package online.niepowazni.scoreboard;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
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

    public String formatted() {
        return "%s 0 - %s 0".formatted(homeTeam, awayTeam);
    }
}
