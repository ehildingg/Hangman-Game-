package com.ehilding.hangman_ehilding;

import android.widget.Toast;

import org.xml.sax.helpers.AttributesImpl;

import java.util.ArrayList;
import java.util.Random;

public class GameEngine {
    private static GameEngine hangmanGame = null;

    private static final String[] WORDS = {
            "ABCDEF", "GOI"
    };
    public static final Random RANDOM = new Random();
    public final int MAX_ERRORS = 7;
    public String wordToFind;
    // public char[] wordFound;
    // public int nbErrors;
    // public ArrayList<String> letters = new ArrayList<>();

    public ArrayList<Character> playerGuess = new ArrayList<>();


    private GameEngine(){

    }

    public static GameEngine getInstance() {
        if (hangmanGame == null) {

            hangmanGame = new GameEngine();

        }

        return hangmanGame;
    }

    public String nextWordToFind() {
        return WORDS[RANDOM.nextInt(WORDS.length)];
    }

    /*private String wordFoundContent() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < wordFound.length; i++) {
            builder.append(wordFound[i]);

            if (i < wordFound.length - 1) {
                builder.append("");
            }
        }
        return builder.toString();
    }*/



    /*public void playerTakesTurn() {

        for (int i = 0; i < wordToFind.length(); i++) {

            if (playerGuess.contains(wordToFind.charAt(i))) {
                GameActivity.setWordToGuess(wordToFind.charAt(i));
            }
            else {
                GameActivity.setWordToGuess("-");
            }

        }

    }*/

}
