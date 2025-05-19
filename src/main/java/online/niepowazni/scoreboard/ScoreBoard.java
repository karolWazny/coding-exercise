package online.niepowazni.scoreboard;

import java.util.List;

public interface ScoreBoard {
    List<Game> getSummary();
    String formatted();
    void startGame(String homeTeam, String awayTeam);
}
