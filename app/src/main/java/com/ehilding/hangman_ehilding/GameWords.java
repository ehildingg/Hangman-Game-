package com.ehilding.hangman_ehilding;

import java.util.Random;

public class GameWords {
    private static GameWords hangmanGame = null;

    private static final String[] WORDS = {
            "RYSSLAND", "UKRAINA", "FRANKRIKE", "SPANIEN", "SVERIGE", "NORGE", "TYSKLAND", "FINLAND",
            "POLEN", "ITALIEN", "STORBRITANNIEN", "RUMÄNIEN", "BELARUS", "GREKLAND", "BULGARIEN", "ISLAND",
            "UNGERN", "PORTUGAL", "ÖSTERRIKE", "TJECKIEN", "SERBIEN", "IRLAND", "LITAUEN", "LETTLAND",
            "KROATIEN", "BOSNIEN", "SLOVAKIEN", "ESTLAND", "DANMARK", "NEDERLÄNDERNA", "SCHWEIZ", "MOLDAVIEN",
            "BELGIEN", "ALBANIEN", "NORDMAKEDONIEN", "TURKIET", "SLOVENIEN", "MONTENEGRO", "KOSOVO",
            "AZERBAJDZJAN", "GEORGIEN", "LUXEMBURG", "ANDORRA", "MALTA", "LIECHTENSTEIN", "MONACO", "VATIKANSTATEN"
    };
    public static final Random RANDOM = new Random();
    public final int MAX_ERRORS = 7;

    private GameWords(){

    }

    public static GameWords getInstance() {
        if (hangmanGame == null) {

            hangmanGame = new GameWords();

        }

        return hangmanGame;
    }

    public String nextWordToFind() {
        return WORDS[RANDOM.nextInt(WORDS.length)];
    }


}
