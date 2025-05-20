package online.niepowazni.scoreboard.dto;

import lombok.Builder;

@Builder
public record TeamPair (
        String home,
        String away
) {}
