package com.ehilding.hangman_ehilding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    ImageView hangMan;
    TextView textAttempts, textAttemptsTicker, textWordToFind;
    EditText userCharInput;
    Button buttonGuess;
    Button buttonNewGame;
    GameEngine game = GameEngine.getInstance();
    public char userInput;
    public ArrayList<Character> playerGuesses = new ArrayList<>();
    public static String wordToFind;
    public static String wordHint;
    public int nbErrors = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        initializeScreen();
        setupOnClickers();

    }

    private void setupOnClickers() {

        buttonNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonNewGame.setVisibility(View.GONE);
                newGame();
            }
        });

        buttonGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playerGuess();
                userCharInput.setText("");

            }
        });
    }

    private void initializeScreen() {
        textAttempts = findViewById(R.id.text_attempts);
        textAttemptsTicker = findViewById(R.id.text_attempts_ticker);
        textWordToFind = findViewById(R.id.word_to_find);
        userCharInput = findViewById(R.id.editxt_enter_char);
        buttonGuess = findViewById(R.id.btn_guess);
        hangMan = findViewById(R.id.img_hang_one);
        buttonNewGame = findViewById(R.id.btn_new_game);

        updateUi();

    }

    private void updateUi() {
        switch (nbErrors) {
            case 0: hangMan.setImageResource(0);
            textAttemptsTicker.setText(String.valueOf(nbErrors));
            break;
            case 1: hangMan.setImageResource(R.drawable.game0);
            textAttemptsTicker.setText(String.valueOf(nbErrors));
            break;
            case 2: hangMan.setImageResource(R.drawable.game1);
            textAttemptsTicker.setText(String.valueOf(nbErrors));
            break;
            case 3: hangMan.setImageResource(R.drawable.game2);
            textAttemptsTicker.setText(String.valueOf(nbErrors));
            break;
            case 4: hangMan.setImageResource(R.drawable.game3);
            textAttemptsTicker.setText(String.valueOf(nbErrors));
            break;
            case 5: hangMan.setImageResource(R.drawable.game4);
            textAttemptsTicker.setText(String.valueOf(nbErrors));
            break;
            case 6: hangMan.setImageResource(R.drawable.game5);
            textAttemptsTicker.setText(String.valueOf(nbErrors));
            break;
            case 7: hangMan.setImageResource(R.drawable.game6);
            textAttemptsTicker.setText(String.valueOf(nbErrors));
            break;
            case 8: hangMan.setImageResource(R.drawable.game7);
            textAttemptsTicker.setText(String.valueOf(nbErrors));



        }
    }

    public void newGame() {

        wordToFind = game.nextWordToFind();

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < wordToFind.length(); i++) {
            builder.append('-');
        }
        wordHint = builder.toString();
        textWordToFind.setText(wordHint);

        playsGame();

    }

    public void playsGame() {



    }

    private void playerGuess() {

        if (userCharInput.length() == 1) {
            userInput = userCharInput.getText().toString().charAt(0);
            playerGuesses.add(userInput);

            if (wordToFind.contains(String.valueOf(userInput).toUpperCase())) {

                StringBuilder builder = new StringBuilder(wordHint);

                for (int i = 0; i < wordToFind.length(); i++) {
                    if (wordToFind.charAt(i) == userInput) {
                        builder.replace(i, i, String.valueOf(userInput));
                        wordHint = builder.toString();
                    }
                }
                textWordToFind.setText(wordHint);
                Toast.makeText(this, "Correct Guess!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Wrong Guess!", Toast.LENGTH_SHORT).show();
                nbErrors++;
            }

        } else {
            Toast.makeText(this, "Input a letter!", Toast.LENGTH_SHORT).show();

        }

        updateUi();

    }

}