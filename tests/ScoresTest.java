import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoresTest {

    @Test
    void shouldSaveUserTotalScore() {
        Scores s = new Scores();
        s.setUserScore("123", 120);
        s.setUserScore("124", 150);
        Assertions.assertEquals(120, s.getAbsolutScores("123"));
        Assertions.assertEquals(150, s.getAbsolutScores("124"));
    }
}