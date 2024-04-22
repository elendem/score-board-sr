package com.elendem.sr.scoreboard.application;

import com.elendem.sr.scoreboard.model.Match;
import com.elendem.sr.scoreboard.model.MatchKey;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

public class WorldCupScoreBoard implements FootballScoreBoard {

    private Map<MatchKey, Match> matchesMap;

    public WorldCupScoreBoard(){
        matchesMap = new ConcurrentHashMap();
    }

    @Override
    public List<Match> getMatches() {
        return matchesMap.values().stream().toList();
    }

    @Override
    public void startMatch(Match match) {

        MatchKey matchKey = new MatchKey(match);

        if(matchKey.getKeyHome().equals(matchKey.getKeyGuest())){
            throw new IllegalArgumentException("Provided teams are duplicated");
        }

        if(checkIfTeamIsPlaying(matchKey)){
            throw new IllegalArgumentException("Provided team(s) game is already in progress");
        }
        matchesMap.put(matchKey, match);
    }

    private boolean checkIfTeamIsPlaying(MatchKey matchKey){

        if(matchesMap.containsKey(matchKey)){
            return true;
        }

        final Predicate<MatchKey> homeCond = m -> m.getKeyHome().equals(matchKey.getKeyHome()) ||
                m.getKeyHome().equals(matchKey.getKeyGuest());
        final Predicate<MatchKey> guestCond = m -> m.getKeyGuest().equals(matchKey.getKeyHome()) ||
                m.getKeyGuest().equals(matchKey.getKeyGuest());
        MatchKey containingKey =matchesMap.keySet().stream().filter(homeCond.or(guestCond))
                .findFirst().orElse(null);

        if(containingKey != null){
            return true;
        }
        return false;
    }

}
