package online.niepowazni.scoreboard;

import java.util.List;

public interface ScoreBoard {
    List<Object> getSummary();
    String formatted();
}
