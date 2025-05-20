package online.niepowazni.scoreboard;

import online.niepowazni.scoreboard.dto.GameDto;
import online.niepowazni.scoreboard.dto.Score;
import online.niepowazni.scoreboard.dto.TeamPair;

import java.util.List;

public interface ScoreBoard {

    List<GameDto> getSummary();
    String formattedSummary();

    void startGame(String homeTeam, String awayTeam);
    void finishGame(String homeTeam, String awayTeam);

    void updateScore(TeamPair teams, Score score);

    static ScoreBoard getInstance() {
        return new InMemoryScoreBoard();
    }
}
