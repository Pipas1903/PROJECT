package com.arcade;
import com.arcade.games.rockPaperScissors.RockPaperScissors;
import com.arcade.menu.Menu;
import com.arcade.player.Player;

import java.io.*;

public class Main {


    public static void main(String[] args) throws IOException {
       // new Menu().on();
        RockPaperScissors game = new RockPaperScissors(new Player("intell"), new Player("terminal"));
        game.startGame2Players();
    }

}


