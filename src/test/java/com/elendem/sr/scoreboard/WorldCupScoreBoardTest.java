package com.elendem.sr.scoreboard;

import com.elendem.sr.scoreboard.application.WorldCupScoreBoard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WorldCupScoreBoardTest {

    @Test
    public void emptyScoreboard_zeroMatches(){
        var scoreboard = new WorldCupScoreBoard();
        final Integer zeroMatches = 0;
        Assertions.assertEquals(scoreboard.getMatches().size(), zeroMatches);
    }

}
