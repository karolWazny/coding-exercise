package online.niepowazni.scoreboard;

import online.niepowazni.scoreboard.dto.GameDto;
import online.niepowazni.scoreboard.dto.Score;
import online.niepowazni.scoreboard.dto.TeamPair;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoardImpl implements ScoreBoard {

    private final List<Game> games = new ArrayList<>();

    @Override
    public List<GameDto> getSummary() {
        return games.stream()
                .map(Game::toGameDto)
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
        games.removeIf(game -> game.isMatchBetween(homeTeam, awayTeam));
    }

    @Override
    public void updateScore(TeamPair teams, Score score) {
        games
                .stream()
                .filter(game -> game.isMatchBetween(teams.home(), teams.away()))
                .forEach(game -> game.setScore(score));
    }
}
