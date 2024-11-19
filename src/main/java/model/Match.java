package model;

public class Match {
    private final String homeTeam;
    private final String awayTeam;
    private int homeScore;
    private int awayScore;
    private int matchOrder;
    public Match(String homeTeam, String awayTeam,int matchOrder) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = 0;
        this.awayScore = 0;
        this.matchOrder = matchOrder; 
    }

    public void updateScore(int homeScore, int awayScore) {
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public int getTotalScore() {
        return homeScore + awayScore;
    }

    public int getMatchOrder() {
        return matchOrder; // Zwracamy numer porządkowy
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    @Override
    public String toString() {
        return homeTeam + " " + homeScore + " - " + awayScore + " " + awayTeam;
    }
}
