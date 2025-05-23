package online.niepowazni.scoreboard;

import online.niepowazni.scoreboard.dto.GameDto;
import online.niepowazni.scoreboard.dto.Score;
import online.niepowazni.scoreboard.dto.TeamPair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class InMemoryScoreBoard implements ScoreBoard {

    private final List<Game> games = new LinkedList<>();

    @Override
    public List<GameDto> getSummary() {
        return getSortedGames()
                .map(Game::toGameDto)
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
        checkIfTeamsCanPlay(homeTeam, awayTeam);
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

    private void checkIfTeamsCanPlay(String homeTeam, String awayTeam) {
        games.stream()
                .flatMap(game -> Stream.of(
                        game.getAwayTeam(), game.getHomeTeam()
                ))
                .filter(name -> Objects.equals(name, homeTeam) || Objects.equals(name, awayTeam))
                .findAny()
                .ifPresent((name) -> {
                    throw new IllegalStateException("Team %s is already playing a match!".formatted(name));
                });
    }

    private Stream<Game> getSortedGames() {
        return games.stream()
                .sorted();
    }
}
