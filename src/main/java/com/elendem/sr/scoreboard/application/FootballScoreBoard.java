package com.elendem.sr.scoreboard.application;

import com.elendem.sr.scoreboard.model.Match;

import java.util.List;

public interface FootballScoreBoard {

    List<Match> getMatches();
    void startMatch(Match match);
}
