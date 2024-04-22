package com.elendem.sr.scoreboard.application;

import com.elendem.sr.scoreboard.model.Match;
import com.elendem.sr.scoreboard.model.MatchKey;

import java.util.List;

public interface FootballScoreBoard {

    List<Match> getMatches();
    void startMatch(Match match);
    Match updateMatchScore(MatchKey matchKey, Integer scoreHome, Integer scoreGuest);
}
