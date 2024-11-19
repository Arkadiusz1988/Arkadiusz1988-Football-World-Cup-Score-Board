package serviceTest;

import org.junit.Before;
import org.junit.Test;
import service.ScoreBoardService;
import service.ScoreBoardServiceImpl;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class ScoreBoardServiceImplTest {

    private ScoreBoardService scoreBoard;

    @Before
    public void setUp() {
        scoreBoard = new ScoreBoardServiceImpl();
    }

    @Test
    public void testStartGame() {
        scoreBoard.startGame("Mexico", "Canada");
        List<String> summary = scoreBoard.getSummary();
        assertEquals(1, summary.size());
        assertEquals("Mexico 0 - 0 Canada", summary.get(0));
    }

    @Test
    public void testStartGameThrowsExceptionWhenGameAlreadyExists() {
        scoreBoard.startGame("Mexico", "Canada");
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> scoreBoard.startGame("Mexico", "Canada"));
        assertEquals("Match already exists.", exception.getMessage());
    }

    @Test
    public void testFinishGame() {
        scoreBoard.startGame("Mexico", "Canada");
        scoreBoard.finishGame("Mexico", "Canada");
        List<String> summary = scoreBoard.getSummary();
        assertTrue(summary.isEmpty());
    }

    @Test
    public void testFinishGameThrowsExceptionWhenGameNotFound() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> scoreBoard.finishGame("Mexico", "Canada"));
        assertEquals("Match not found.", exception.getMessage());
    }

    @Test
    public void testUpdateScore() {
        scoreBoard.startGame("Mexico", "Canada");
        scoreBoard.updateScore("Mexico", "Canada", 3, 2);
        List<String> summary = scoreBoard.getSummary();
        assertEquals("Mexico 3 - 2 Canada", summary.get(0));
    }

    @Test
    public void testUpdateScoreThrowsExceptionWhenGameNotFound() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> scoreBoard.updateScore("Mexico", "Canada", 3, 2));
        assertEquals("Match not found.", exception.getMessage());
    }

    @Test
    public void testGetSummarySortedByTotalScoreAndTimestamp() {
        scoreBoard.startGame("Mexico", "Canada");
        scoreBoard.startGame("Spain", "Brazil");
        scoreBoard.startGame("Uruguay", "Italy");

        scoreBoard.updateScore("Mexico", "Canada", 0, 5);
        scoreBoard.updateScore("Spain", "Brazil", 3, 3);
        scoreBoard.updateScore("Uruguay", "Italy", 1, 1);

        List<String> summary = scoreBoard.getSummary();

        assertEquals("Spain 3 - 3 Brazil", summary.get(0));
        assertEquals("Mexico 0 - 5 Canada", summary.get(1));
        assertEquals("Uruguay 1 - 1 Italy", summary.get(2));
    }

    @Test
    public void testGetSummaryWithSameTotalScoreOrdersByTimestamp() {
        scoreBoard.startGame("Game1", "TeamA");
        scoreBoard.startGame("Game2", "TeamB");

        scoreBoard.updateScore("Game1", "TeamA", 2, 2);
        scoreBoard.updateScore("Game2", "TeamB", 2, 2);

        List<String> summary = scoreBoard.getSummary();

        assertEquals("Game2 2 - 2 TeamB", summary.get(0));
        assertEquals("Game1 2 - 2 TeamA", summary.get(1));
    }
}
