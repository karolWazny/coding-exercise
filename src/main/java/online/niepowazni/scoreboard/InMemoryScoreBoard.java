package online.niepowazni.scoreboard;

import online.niepowazni.scoreboard.dto.GameDto;
import online.niepowazni.scoreboard.dto.Score;
import online.niepowazni.scoreboard.dto.TeamPair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class InMemoryScoreBoard implements ScoreBoard {

    private final List<Game> games = new ArrayList<>();

    @Override
    public List<GameDto> getSummary() {
        return getSortedGames()
                .map(InMemoryScoreBoard::mapToGameDto)
                .toList();
    }

    @Override
    public String formattedSummary() {
        List<String> immutableLines = getSortedGames()
                .map(Game::formatted)
                .toList();
        List<String> lines = new ArrayList<>(immutableLines);
        IntStream.range(0, lines.size())
                .forEach(index -> {
                    String indexedValue = "%d. %s".formatted(index + 1, lines.get(index));
                    lines.set(index, indexedValue);
                });
        return String.join("\n", lines);
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

    private Stream<Game> getSortedGames() {
        return games.stream()
                .sorted();
    }

    private static GameDto mapToGameDto(Game game) {
        return GameDto.builder()
                .awayTeam(game.getAwayTeam())
                .homeTeam(game.getHomeTeam())
                .homeTeamScore(game.getHomeScore())
                .awayTeamScore(game.getAwayScore())
                .build();
    }
}
