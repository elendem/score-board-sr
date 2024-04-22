package com.elendem.sr.scoreboard.application;

import java.util.ArrayList;
import java.util.List;

public class WorldCupScoreBoard implements FootballScoreBoard {

    private List<Object> matchesMap;

    public WorldCupScoreBoard(){
        matchesMap = new ArrayList<>();
    }

    @Override
    public List<Object> getMatches() {
        return matchesMap.stream().toList();
    }

}
