package com.arcade;

import com.arcade.games.ticTacToe.TicTacToe;
import com.arcade.menu.Menu;
import com.arcade.player.PlayerManager;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {
        //TicTacToe game = new TicTacToe(PlayerManager.playerSelection(), PlayerManager.playerSelection());
        //game.startGame2Players();

        new Menu().on();
    }

}


