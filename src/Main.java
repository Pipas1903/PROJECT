import Arcade.Arcade;
import Common.Messages;
import Player.PlayerManager;

public class Main {
    public static void main(String[] args) {
        new Arcade().secondMenu();
        PlayerManager.addScoreToPlayerFile("pip", 98765, Messages.TIC_TAC_TOE);
        PlayerManager.addScoreToPlayerFile("pip", 9876, Messages.TIC_TAC_TOE);
        PlayerManager.addScoreToPlayerFile("pip", 5, Messages.ROCK_PAPER_SCISSORS);
        //System.out.println(PlayerManager.readFromPlayerFile("pip"));
    }
}
