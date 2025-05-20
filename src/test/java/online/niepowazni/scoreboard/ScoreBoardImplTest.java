package online.niepowazni.scoreboard;

import online.niepowazni.scoreboard.dto.GameDto;
import online.niepowazni.scoreboard.dto.Score;
import online.niepowazni.scoreboard.dto.TeamPair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ScoreBoardImplTest {

    private ScoreBoard scoreBoard;

    @BeforeEach
    public void prepareEmptyBoard() {
        scoreBoard = new ScoreBoardImpl();
    }

    @Test
    @DisplayName("Fresh score board returns empty list of games")
    public void freshScoreBoardIsEmpty() {
        List<GameDto> games = scoreBoard.getSummary();
        Assertions.assertNotNull(games);
        Assertions.assertEquals(0, games.size());
    }

    @Test
    @DisplayName("Fresh score board is formatted as empty string")
    public void formatEmptyBoardTest() {
        Assertions.assertEquals("", scoreBoard.formattedSummary());
    }

    @Test
    @DisplayName("ScoreBoard returns started games in a summary")
    public void scoreBoardReturnsGames() {
        scoreBoard.startGame("Poland", "Germany");
        Assertions.assertEquals(List.of(
                GameDto.builder()
                        .homeTeam("Poland")
                        .homeTeamScore(0)
                        .awayTeam("Germany")
                        .awayTeamScore(0)
                        .build()
        ), scoreBoard.getSummary());
    }

    @Test
    @DisplayName("ScoreBoard does not return finished games in the summary, unfinished games remain")
    public void startTwoGamesFinishOneGame() {
        // given
        scoreBoard.startGame("Poland", "Germany");
        scoreBoard.startGame("Sweden", "Denmark");
        // when
        scoreBoard.finishGame("Poland", "Germany");
        // then
        Assertions.assertEquals(List.of(
                GameDto.builder()
                        .homeTeam("Sweden")
                        .homeTeamScore(0)
                        .awayTeam("Denmark")
                        .awayTeamScore(0)
                        .build()
        ), scoreBoard.getSummary());
    }

    @Test
    @DisplayName("Match score can be updated")
    public void updateOnlyOneMatchScoreTest() {
        // given
        scoreBoard.startGame("Poland", "Germany");
        scoreBoard.startGame("Sweden", "Denmark");

        // when
        scoreBoard.updateScore(TeamPair.builder()
                        .home("Poland")
                        .away("Germany")
                        .build(),
                Score.builder()
                        .home(2)
                        .away(1)
                        .build());

        // then
        Assertions.assertEquals(List.of(
                GameDto.builder()
                        .homeTeamScore(2)
                        .awayTeamScore(1)
                        .homeTeam("Poland")
                        .awayTeam("Germany")
                        .build(),
                GameDto.builder()
                        .homeTeam("Sweden")
                        .homeTeamScore(0)
                        .awayTeam("Denmark")
                        .awayTeamScore(0)
                        .build()
        ), scoreBoard.getSummary());
    }

    @Test
    @DisplayName("Display formatted summary with one game")
    public void formattedSummaryOneGame() {
        scoreBoard.startGame("Poland", "Germany");
        String summary = scoreBoard.formattedSummary();
        Assertions.assertEquals("1. Poland 0 - Germany 0", summary);
    }

    @Test
    @DisplayName("Display formatted summary with two games")
    public void formattedSummaryTwoGames() {
        scoreBoard.startGame("Poland", "Germany");
        scoreBoard.startGame("Sweden", "Denmark");
        String summary = scoreBoard.formattedSummary();
        Assertions.assertEquals("1. Poland 0 - Germany 0\n2. Sweden 0 - Denmark 0", summary);
    }

    @Test
    @DisplayName("Formatted summary is sorted by total score")
    public void formattedSummarySortedByScore() {
        scoreBoard.startGame("Poland", "Germany");
        scoreBoard.startGame("Sweden", "Denmark");
        scoreBoard.startGame("England", "Scotland");
        scoreBoard.updateScore(TeamPair.builder()
                        .home("Sweden")
                        .away("Denmark")
                        .build(),
                Score.builder()
                        .home(1)
                        .away(1)
                        .build());
        String summary = scoreBoard.formattedSummary();
        Assertions.assertEquals("1. Sweden 1 - Denmark 1\n2. Poland 0 - Germany 0\n3. England 0 - Scotland 0", summary);
    }

    @Test
    @DisplayName("API summary is sorted by total score")
    public void summarySortedByScore() {
        // given
        scoreBoard.startGame("Poland", "Germany");
        scoreBoard.startGame("Sweden", "Denmark");
        scoreBoard.startGame("England", "Scotland");

        // when
        scoreBoard.updateScore(TeamPair.builder()
                        .home("Sweden")
                        .away("Denmark")
                        .build(),
                Score.builder()
                        .home(2)
                        .away(1)
                        .build());

        // then
        Assertions.assertEquals(
                List.of(
                        GameDto.builder()
                                .homeTeam("Sweden")
                                .homeTeamScore(2)
                                .awayTeam("Denmark")
                                .awayTeamScore(1)
                                .build(),
                        GameDto.builder()
                                .homeTeam("Poland")
                                .homeTeamScore(0)
                                .awayTeam("Germany")
                                .awayTeamScore(0)
                                .build(),
                        GameDto.builder()
                                .homeTeam("England")
                                .homeTeamScore(0)
                                .awayTeam("Scotland")
                                .awayTeamScore(0)
                                .build()
                ),
                scoreBoard.getSummary()
        );
    }
}
