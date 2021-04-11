import java.util.*;
import java.util.regex.Pattern;

public class RankingService {

    HashMap<String, Integer> scores_dict = new HashMap<String, Integer>();

    public void setUserScore(String user, String points) {
        if (userExists(user)) {
            int actualUserPoints = scores_dict.get(user);
            int num = Math.abs(Integer.parseInt(points));
            if (isSubtraction(points)) {
                int score = actualUserPoints - num;
                scores_dict.put(user, score);
            } else if (isSum(points)) {
                int score = actualUserPoints + num;
                scores_dict.put(user, score);
            } else {
                scores_dict.put(user, num);
            }
        } else {
            scores_dict.put(user, Integer.parseInt(points));
        }

    }

    private boolean isSum(String points) {
        return Character.compare(points.charAt(0), '+') == 0;
    }

    private boolean isSubtraction(String points) {
        return Character.compare(points.charAt(0), '-') == 0;
    }

    private boolean userExists(String user) {
        return scores_dict.containsKey(user);
    }

    public int getAbsolutScores(String user) {
       return scores_dict.get(user);
    }

    public List<User> getAbsolutesRanking(int i) {
        List<User> usersSortByScore = getSortedScores();

        List<User> rankings = new ArrayList<>();
        int n = 0;
        while (n < i) {
            rankings.add( usersSortByScore.get(n));
            n++;
        }
        return rankings;
    }

    public List<User> getRelativeRanking(String s) {
        String[] result = s.split(Pattern.quote("/"));
        int points = Integer.parseInt(result[0]);
        int range = Integer.parseInt(result[1]);
        List<User> usersSortByScore = getSortedScores();
        List<User> rankings = new ArrayList<>();

        for (int i = 0; i < usersSortByScore.size(); i++) {
            if (usersSortByScore.get(i).getScore() == points) {
                rankings.add(usersSortByScore.get(i));
                addRankingInRange(range, usersSortByScore, rankings, i);
                rankings.sort(Comparator.comparing(User::getScore));
                return rankings;
            }
        }
        return rankings;
    }

    private void addRankingInRange(int range, List<User> usersSortByScore, List<User> rankings, int i) {
        while (range > 0) {
            if (i + range < usersSortByScore.size()) {
                rankings.add(usersSortByScore.get(i + range));
            }
            if (i - range >= 0) {
                rankings.add(usersSortByScore.get(i - range));
            }
            range--;
        }
    }

    private List<User> getSortedScores() {
        List<User> usersSortByScore = new ArrayList<>();
        scores_dict.forEach((user, score) -> usersSortByScore.add(new User(user, score)));
        usersSortByScore.sort(Comparator.comparing(User::getScore).reversed());
        return usersSortByScore;
    }
}
