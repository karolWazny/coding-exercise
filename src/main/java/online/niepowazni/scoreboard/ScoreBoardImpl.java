package online.niepowazni.scoreboard;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoardImpl implements ScoreBoard {

    private List<Game> games = new ArrayList<>();

    @Override
    public List<Game> getSummary() {
        return games;
    }

    @Override
    public String formatted() {
        return "";
    }

    @Override
    public void startGame(String homeTeam, String awayTeam) {
        games.add(Game.builder()
                .homeTeam(homeTeam)
                .awayTeam(awayTeam)
                .build());
    }
}
