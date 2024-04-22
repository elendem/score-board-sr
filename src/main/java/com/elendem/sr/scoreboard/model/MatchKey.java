package com.elendem.sr.scoreboard.model;

import java.util.Objects;

public class MatchKey {

    String keyHome;
    String keyGuest;

    public MatchKey(String keyHome, String keyGuest){
        this.keyHome = keyHome.toLowerCase();
        this.keyGuest = keyGuest.toLowerCase();
    }

    public MatchKey(Match match){
        this.keyHome = match.getHome().teamIdentifier().toLowerCase();
        this.keyGuest = match.getGuest().teamIdentifier().toLowerCase();
    }

    public String getKeyHome() {
        return keyHome;
    }

    public String getKeyGuest() {
        return keyGuest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchKey matchKey = (MatchKey) o;
        return Objects.equals(keyHome, matchKey.keyHome) && Objects.equals(keyGuest, matchKey.keyGuest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyHome, keyGuest);
    }
}
