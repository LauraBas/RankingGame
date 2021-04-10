import java.util.Hashtable;

public class Scores {

    Hashtable<String, Integer> scores_dict = new Hashtable<String, Integer>();

    public void setUserScore(String user, int points) {
        scores_dict.put(user,points);
    }

    public int getAbsolutScores(String user) {
       return scores_dict.get(user);
    }
}
