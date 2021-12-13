package Player;

public class Player {

    private String nickName;
    private int score;


    public Player(String nickName) {
        this.nickName = nickName;
        this.score = 0;
    }

    public String getNickName() {
        return nickName;
    }

    public int getCurrentScore() {
        return score;
    }

    public void setCurrentScore(int currentScore) {
        this.score = currentScore;
    }
}
