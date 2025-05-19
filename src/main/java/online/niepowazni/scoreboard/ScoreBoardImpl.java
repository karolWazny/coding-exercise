package online.niepowazni.scoreboard;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoardImpl implements ScoreBoard {

    @Override
    public List<Object> getSummary() {
        return new ArrayList<>();
    }

    @Override
    public String formatted() {
        return "";
    }
}
