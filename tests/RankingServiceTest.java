import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class RankingServiceTest {

    @Test
    void shouldSaveUserTotalScore() {
        RankingService rankingService = new RankingService();
        rankingService.setUserScore("123", "120");
        rankingService.setUserScore("124", "150");
        Assertions.assertEquals(120, rankingService.getAbsolutScores("123"));
        Assertions.assertEquals(150, rankingService.getAbsolutScores("124"));
    }

    @Test
    void shouldSaveUserRelativeScore() {
        RankingService rankingService = new RankingService();
        rankingService.setUserScore("123", "120");
        rankingService.setUserScore("123", "-20");
        rankingService.setUserScore("123", "+30");
        Assertions.assertEquals(130, rankingService.getAbsolutScores("123"));
    }

    @Test
    void shouldReturnAbsolutesRankings() {
        RankingService rankingService = new RankingService();
        rankingService.setUserScore("123", "120");
        rankingService.setUserScore("124", "130");
        rankingService.setUserScore("125", "140");
        rankingService.setUserScore("126", "100");

        List<User> expected = new ArrayList<>();
        expected.add(new User("125", 140));
        expected.add(new User("124", 130));
        expected.add(new User("123", 120));
        assertThat(rankingService.getAbsolutesRanking(3), is(expected));
    }

    @Test
    void shouldReturnRelativeRanking() {
        RankingService rankingService = new RankingService();
        rankingService.setUserScore("128", "1000");
        rankingService.setUserScore("129", "980");
        rankingService.setUserScore("125", "102");
        rankingService.setUserScore("124", "101");
        rankingService.setUserScore("123", "100");
        rankingService.setUserScore("126", "99");
        rankingService.setUserScore("127", "98");

        List<User> expected = new ArrayList<>();
        expected.add(new User("126", 99));
        expected.add(new User("123", 100));
        expected.add(new User("124", 101));
        expected.add(new User("125", 102));
        expected.add(new User("129", 980));
        assertThat(rankingService.getRelativeRanking("4/2"), is(expected));
    }

    @Test
    void shouldReturnRelativeRankingIfExists() {
        RankingService rankingService = new RankingService();
        rankingService.setUserScore("123", "100");
        rankingService.setUserScore("126", "99");
        rankingService.setUserScore("127", "98");

        List<User> expected = new ArrayList<>();
        expected.add(new User("127", 98));
        expected.add(new User("126", 99));
        expected.add(new User("123", 100));

        assertThat(rankingService.getRelativeRanking("1/2"), is(expected));
    }

}