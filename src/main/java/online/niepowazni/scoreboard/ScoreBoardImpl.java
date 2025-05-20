package online.niepowazni.scoreboard;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoardImpl implements ScoreBoard {

    private List<Game> games = new ArrayList<>();

    @Override
    public List<GameDto> getSummary() {
        return games.stream()
                .map(game -> GameDto.builder()
                        .awayTeam(game.getAwayTeam())
                        .homeTeam(game.getHomeTeam())
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
        games = new ArrayList<>();
    }
}
