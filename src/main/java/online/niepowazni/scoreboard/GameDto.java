package online.niepowazni.scoreboard;

import lombok.Builder;

@Builder
public record GameDto(
        String homeTeam,
        String awayTeam,
        int homeTeamScore,
        int awayTeamScore
) {}
