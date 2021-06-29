package high_scores;

public class HighScoreRecord implements Comparable<HighScoreRecord> {
    private static int iter = 0;
    public int no;
    public String username;
    public int score;

    public HighScoreRecord(String username, int score){
        no = ++iter;
        this.username = username;
        this.score = score;
    }

    public void setNo(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return no+" "+username+" "+score;
    }

    @Override
    public int compareTo(HighScoreRecord hs) {
        if (this.score > hs.score){
            return -1;
        }else if (this.score < hs.score){
            return 1;
        }else{
            return 0;
        }
    }
}
