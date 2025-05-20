package online.niepowazni.scoreboard;

import java.util.List;

public interface ScoreBoard {
    List<GameDto> getSummary();
    String formattedSummary();
    void startGame(String homeTeam, String awayTeam);
}
