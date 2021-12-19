package com.arcade;
import com.arcade.games.rockPaperScissors.RockPaperScissors;
import com.arcade.menu.Menu;
import com.arcade.player.Player;

import java.io.*;

public class Main {


    public static void main(String[] args) throws IOException {
       // new Menu().on();
        RockPaperScissors game = new RockPaperScissors(new Player("me"), new Player("terminal me"));
        game.startGame2Players();
    }

}


