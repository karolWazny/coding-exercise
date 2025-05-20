package online.niepowazni.scoreboard;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import online.niepowazni.scoreboard.dto.GameDto;
import online.niepowazni.scoreboard.dto.Score;

import java.util.Objects;

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

    public boolean isMatchBetween(String homeTeam, String awayTeam) {
        return Objects.equals(this.homeTeam, homeTeam) && Objects.equals(this.awayTeam, awayTeam);
    }

    public GameDto toGameDto() {
        return GameDto.builder()
                .awayTeam(awayTeam)
                .homeTeam(homeTeam)
                .homeTeamScore(homeScore)
                .awayTeamScore(awayScore)
                .build();
    }
}
