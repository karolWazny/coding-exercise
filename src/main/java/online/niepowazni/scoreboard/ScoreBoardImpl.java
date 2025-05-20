package online.niepowazni.scoreboard;

import online.niepowazni.scoreboard.dto.GameDto;
import online.niepowazni.scoreboard.dto.Score;
import online.niepowazni.scoreboard.dto.TeamPair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ScoreBoardImpl implements ScoreBoard {

    private List<Game> games = new ArrayList<>();

    @Override
    public List<GameDto> getSummary() {
        return games.stream()
                .map(game -> GameDto.builder()
                        .awayTeam(game.getAwayTeam())
                        .homeTeam(game.getHomeTeam())
                        .homeTeamScore(game.getHomeScore())
                        .awayTeamScore(game.getAwayScore())
                        .build())
                .toList();
    }

    @Override
    public String formattedSummary() {
        return "";
    }

    @Override
    public void startGame(String homeTeam, String awayTeam) {
        games.add(Game.builder()
                .homeTeam(homeTeam)
                .awayTeam(awayTeam)
                .build());
    }

    @Override
    public void finishGame(String homeTeam, String awayTeam) {
        games.removeIf(game -> Objects.equals(homeTeam, game.getHomeTeam()) && Objects.equals(awayTeam, game.getAwayTeam()));
    }

    @Override
    public void updateScore(TeamPair teams, Score score) {
        games.forEach(game -> game.setScore(score));
    }
}
