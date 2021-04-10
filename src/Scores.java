import java.util.Hashtable;

public class Scores {

    Hashtable<String, Integer> scores_dict = new Hashtable<String, Integer>();

    public void setUserScore(String user, String points) {
        if (userExists(user)) {
            int actualUserPoints = scores_dict.get(user);
            int num = Math.abs(Integer.parseInt(points));
            if (isSubstraction(points)) {
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

    private boolean isSubstraction(String points) {
        return Character.compare(points.charAt(0), '-') == 0;
    }

    private boolean userExists(String user) {
        return scores_dict.containsKey(user);
    }

    public int getAbsolutScores(String user) {
       return scores_dict.get(user);
    }
}
