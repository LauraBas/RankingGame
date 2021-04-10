import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class ScoresTest {

    @Test
    void shouldSaveUserTotalScore() {
        Scores s = new Scores();
        s.setUserScore("123", "120");
        s.setUserScore("124", "150");
        Assertions.assertEquals(120, s.getAbsolutScores("123"));
        Assertions.assertEquals(150, s.getAbsolutScores("124"));
    }

    @Test
    void shouldSaveUserRelativeScore() {
        Scores s = new Scores();
        s.setUserScore("123", "120");
        s.setUserScore("123", "-20");
        s.setUserScore("123", "+30");
        Assertions.assertEquals(130, s.getAbsolutScores("123"));
    }

    @Test
    void shouldReturnAbsolutesRankings() {
        Scores s = new Scores();
        s.setUserScore("123", "120");
        s.setUserScore("124", "130");
        s.setUserScore("125", "140");
        s.setUserScore("126", "100");

        List<User> expected = new ArrayList<>();
        expected.add(new User("125", 140));
        expected.add(new User("124", 130));
        expected.add(new User("123", 120));
        assertThat(s.getAbsolutesRanking(3), is(expected));
    }

}