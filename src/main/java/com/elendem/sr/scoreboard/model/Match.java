package com.elendem.sr.scoreboard.model;

public class Match {


    private Team home;
    private Team guest;
    private Integer homeScore = 0;
    private Integer guestScore = 0;

    public Match(Team home, Team guest){
        validateTeam(home);
        this.home = home;

        validateTeam(guest);
        this.guest = guest;
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

    private void validateTeam(Team team) throws IllegalArgumentException{
        if(team.teamIdentifier() == null || team.teamIdentifier().trim().isEmpty()){
            throw new IllegalArgumentException("Team identifier is not valid");
        }
    }

}
