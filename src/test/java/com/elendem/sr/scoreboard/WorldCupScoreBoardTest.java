package com.elendem.sr.scoreboard;

import com.elendem.sr.scoreboard.application.FootballScoreBoard;
import com.elendem.sr.scoreboard.application.WorldCupScoreBoard;
import com.elendem.sr.scoreboard.model.Match;
import com.elendem.sr.scoreboard.model.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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
    @MethodSource("singleMatchValidTeams")
    public void addMatchToScoreboard_oneMatch(Match match){
        scoreboard.startMatch(match);
        final Integer oneMatch = 1;

        Assertions.assertEquals(scoreboard.getMatches().size(), oneMatch);
    }

    @ParameterizedTest
    @MethodSource("singleMatchValidTeams")
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
    @MethodSource("singleMatchValidTeams")
    public void createNewMatchWithTeams_thenValidateScore(Match match){
        scoreboard.startMatch(match);
        final Integer initTotalScore = 0;
        Assertions.assertEquals(scoreboard.getMatches().getFirst().getTotalScore(), initTotalScore);
    }

    public static Stream<Arguments> singleMatchValidTeams() {
        var team1 = new Team("France");
        var team2 = new Team("Brazil");
        Match match = new Match(team1, team2);
        return Stream.of(Arguments.of(match));
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

}
