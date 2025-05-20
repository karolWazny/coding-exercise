package online.niepowazni.scoreboard.dto;

import lombok.Builder;

@Builder
public record Score(
        int home,
        int away
) {}
