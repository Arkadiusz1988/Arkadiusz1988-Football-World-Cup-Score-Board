package service;

import model.Match;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreBoardServiceImpl implements ScoreBoardService {

    private final List<Match> matches = new ArrayList<>();
    private int matchCounter = 0;

    @Override
    public void startGame(String homeTeam, String awayTeam) {
        if (findMatch(homeTeam, awayTeam) != null) {
            throw new IllegalArgumentException("Match already exists.");
        }
        matches.add(new Match(homeTeam, awayTeam, matchCounter++));
    }

    @Override
    public void finishGame(String homeTeam, String awayTeam) {
        Match match = findMatch(homeTeam, awayTeam);
        if (match == null) {
            throw new IllegalArgumentException("Match not found.");
        }
        matches.remove(match);
    }

    @Override
    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        Match match = findMatch(homeTeam, awayTeam);
        if (match == null) {
            throw new IllegalArgumentException("Match not found.");
        }
        match.updateScore(homeScore, awayScore);
    }

    @Override
    public List<String> getSummary() {
        return matches.stream()
                .sorted((m1, m2) -> {
                    int scoreComparison = Integer.compare(m2.getTotalScore(), m1.getTotalScore());
                    return scoreComparison != 0 ? scoreComparison : Long.compare(m2.getMatchOrder(), m1.getMatchOrder());
                })
                .map(Match::toString)
                .collect(Collectors.toList());
    }

    private Match findMatch(String homeTeam, String awayTeam) {
        for (Match match : matches) {
            if (match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam)) {
                return match;
            }
        }
        return null;
    }
}