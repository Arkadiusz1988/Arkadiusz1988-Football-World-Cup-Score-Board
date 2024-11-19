package service;

import java.util.List;

public interface ScoreBoardService {
    void startGame(String homeTeam, String awayTeam);
    void finishGame(String homeTeam, String awayTeam);
    void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore);
    List<String> getSummary();
}
