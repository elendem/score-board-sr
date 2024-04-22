package com.elendem.sr.scoreboard;

import com.elendem.sr.scoreboard.application.FootballScoreBoard;
import com.elendem.sr.scoreboard.application.WorldCupScoreBoard;
import com.elendem.sr.scoreboard.model.Match;
import com.elendem.sr.scoreboard.model.MatchKey;
import com.elendem.sr.scoreboard.model.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class WorldCupScoreBoardTest {


    private FootballScoreBoard scoreboard;

    @BeforeEach
    public void setUp(){
        scoreboard = new WorldCupScoreBoard();
    }

    @Test
    public void emptyScoreboard_zeroMatches(){
        final Integer zeroMatches = 0;
        Assertions.assertEquals(scoreboard.getMatches().size(), zeroMatches);
    }

    @ParameterizedTest
    @MethodSource("matchValidTeamsProvider")
    public void addMatchToScoreboard_oneMatch(Match match){
        scoreboard.startMatch(match);
        final Integer oneMatch = 1;

        Assertions.assertEquals(scoreboard.getMatches().size(), oneMatch);
    }

    @ParameterizedTest
    @MethodSource("matchValidTeamsProvider")
    public void createNewMatchWithTeams_thenValidateTeams(Match match){
        scoreboard.startMatch(match);
        Assertions.assertEquals(scoreboard.getMatches().getFirst(), match);
    }

    @ParameterizedTest
    @MethodSource("invalidTeamsArgumentRedundant")
    public void createNewMatchWithTeamsRedundant_thenValidateTeams(Match match1, Match match2){
        scoreboard.startMatch(match1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> scoreboard.startMatch(match2));
    }

    @ParameterizedTest
    @MethodSource("invalidTeamsArgumentEmptyNull")
    public void createTeamsWithInvalidInputs_isRejected(Team home, Team guest){
        Assertions.assertThrows(IllegalArgumentException.class,() -> scoreboard
                .startMatch(new Match(home,guest)));
    }

    @ParameterizedTest
    @MethodSource("matchValidTeamsProvider")
    public void createNewMatchWithTeams_thenValidateScore(Match match){
        scoreboard.startMatch(match);
        final Integer initTotalScore = 0;
        Assertions.assertEquals(scoreboard.getMatches().getFirst().getTotalScore(), initTotalScore);
    }

    @ParameterizedTest
    @MethodSource("matchValidTeamsProvider")
    public void updateScoreOfRunningMatch_single(Match match){
        scoreboard.startMatch(match);
        final Integer scoreHome = 3;
        final Integer scoreGuest = 2;
        final var matchKey = new MatchKey(match);
        var updatedMatch = scoreboard.updateMatchScore(matchKey, scoreHome, scoreGuest);
        Assertions.assertEquals(scoreHome + scoreGuest, updatedMatch.getTotalScore());
    }

    @ParameterizedTest
    @MethodSource("matchValidTeamsProvider")
    public void updateScoreOfRunningMatch_invalidScore(Match match){
        scoreboard.startMatch(match);
        final Integer scoreHome = -1;
        final Integer scoreGuest = 2;
        final var matchKey = new MatchKey(match);
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> scoreboard.updateMatchScore(matchKey, scoreHome, scoreGuest));
    }

    @ParameterizedTest
    @MethodSource("matchValidTeamsProvider")
    public void updateScoreOfRunningMatches_updatedReturned(Match match1, Match match2){
        scoreboard.startMatch(match1);
        scoreboard.startMatch(match2);
        final Integer scoreHome = 3;
        final Integer scoreGuest = 2;
        final var matchKey = new MatchKey(match1);
        var updatedMatch = scoreboard.updateMatchScore(matchKey, scoreHome, scoreGuest);
        Assertions.assertEquals(match1, updatedMatch);
    }

    @ParameterizedTest
    @MethodSource("multipleMatchValidTeams")
    public void finishRunningMatch_checkIfAvailable(List<Match> matches){
        matches.forEach((match -> scoreboard.startMatch(match)));
        final var matchKey = new MatchKey(matches.get(0));
        scoreboard.finishMatch(matchKey);
        Assertions.assertEquals(scoreboard.getMatches().size(), matches.size()-1);
    }
    @ParameterizedTest
    @MethodSource("multipleMatchValidTeams")
    public void finishRunningMatchWithInvalidKet_checkIfAvailable(List<Match> matches){
        matches.forEach((match -> scoreboard.startMatch(match)));
        final var matchKey = new MatchKey("RandomKey1", "RandomKey2");
        scoreboard.finishMatch(matchKey);
        Assertions.assertEquals(scoreboard.getMatches().size(), matches.size());
    }

    public static Stream<Arguments> matchValidTeamsProvider() {
        var team1 = new Team("France");
        var team2 = new Team("Brazil");
        var team3 = new Team("Germany");
        var team4 = new Team("Uruguay");
        Match match1 = new Match(team1, team2);
        Match match2= new Match(team3, team4);
        return Stream.of(Arguments.of(match1, match2));
    }

    public static Stream<Arguments> invalidTeamsArgumentEmptyNull() {
        var teamValid = new Team("France");
        var teamNull = new Team(null);
        var teamEmpty = new Team("  ");
        return Stream.of(Arguments.of(teamValid,teamEmpty), Arguments.of(teamNull,teamValid));
    }

    public static Stream<Arguments> invalidTeamsArgumentRedundant() {
        var team1 = new Team("France");
        var team2 = new Team("Brazil");
        var team3 = new Team("Portugal");
        Match match1 = new Match(team1, team2);
        Match match2 = new Match(team3, team1);
        return Stream.of(Arguments.of(match1,match2));
    }

    public static Stream<Arguments> multipleMatchValidTeams() {
        List<String> teams = List.of("Mexico","Canada","Spain","Brazil","Germany","France","Uruguay","Italy",
                "Argentina","Australia");
        var matchList = new ArrayList<Match>();

        Team team1 = null;
        Team team2 = null;
        for (int i = 1; i <= teams.size(); i++){
            if(i % 2 != 0){
                team1 = new Team(teams.get(i-1));
            } else {
                team2 = new Team(teams.get(i-1));
                matchList.add(new Match(team1, team2));
            }
        }
        return Stream.of(Arguments.of(matchList));
    }

}
