package com.ehilding.hangman_ehilding;

import android.widget.Toast;

public class GameEngine {
    private static GameEngine hangmanGame = null;

    private GameEngine(){

    }

    public static GameEngine getInstance() {
        if (hangmanGame == null) {

            hangmanGame = new GameEngine();

        }

        return hangmanGame;
    }

    public static void promptSysout(){
        System.out.println("Testing just ");
    }
}
