package online.niepowazni.scoreboard;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import online.niepowazni.scoreboard.dto.Score;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Game {

    final String homeTeam;
    final String awayTeam;

    int homeScore;
    int awayScore;

    public String formatted() {
        return "%s 0 - %s 0".formatted(homeTeam, awayTeam);
    }

    public void setScore(Score score) {
        homeScore = score.home();
        awayScore = score.away();
    }
}
