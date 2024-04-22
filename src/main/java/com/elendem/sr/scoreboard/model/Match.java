package com.elendem.sr.scoreboard.model;

import java.time.Instant;

public class Match {


    private Team home;
    private Team guest;
    private Integer homeScore = 0;
    private Integer guestScore = 0;
    private Instant matchStartTime;

    public Match(Team home, Team guest){
        validateTeam(home);
        this.home = home;

        validateTeam(guest);
        this.guest = guest;

        matchStartTime = Instant.now();
    }

    public Team getHome() {
        return home;
    }

    public Team getGuest() {
        return guest;
    }

    public void setHomeScore(Integer homeScore) {
        this.homeScore = homeScore;
    }

    public void setGuestScore(Integer guestScore) {
        this.guestScore = guestScore;
    }

    public Integer getTotalScore(){
        return homeScore + guestScore;
    }

    public Instant getMatchStartTime() {
        return matchStartTime;
    }

    private void validateTeam(Team team) throws IllegalArgumentException{
        if(team.teamIdentifier() == null || team.teamIdentifier().trim().isEmpty()){
            throw new IllegalArgumentException("Team identifier is not valid");
        }
    }

    @Override
    public String toString() {
        return "Match{" + "home=" + home +", guest=" + guest +
                ", homeScore=" + homeScore +", guestScore=" + guestScore +
                ", matchStartTime=" + matchStartTime +'}';
    }

}
