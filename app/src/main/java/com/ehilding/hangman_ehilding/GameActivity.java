package com.ehilding.hangman_ehilding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    ImageView hangMan;
    TextView textAttempts, textAttemptsTicker, textWordToFind, textGuessedLetters;
    EditText userCharInput;
    Button buttonGuess;
    Button buttonNewGame;
    GameEngine game = GameEngine.getInstance();
    public char userInput;
    public ArrayList<Character> playerGuesses = new ArrayList<>();
    public static String wordToFind;
    public static String wordHint;
    public int nbErrors = 0;
    StringBuilder wordBuilder = new StringBuilder();

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
                buttonGuess.setVisibility(View.VISIBLE);
                userCharInput.setVisibility(View.VISIBLE);
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
        textGuessedLetters = findViewById(R.id.guessed_letters);
        buttonGuess.setVisibility(View.GONE);
        userCharInput.setVisibility(View.GONE);

        updateUi();

    }

    private void updateUi() {
        switch (nbErrors) {
            /*case 1:
                hangMan.setImageResource(0);
                textAttemptsTicker.setText(String.valueOf(nbErrors));
                break;*/
            case 0:
                hangMan.setImageResource(R.drawable.game0);
                textAttemptsTicker.setText(String.valueOf(nbErrors));
                break;
            case 1:
                hangMan.setImageResource(R.drawable.game1);
                textAttemptsTicker.setText(String.valueOf(nbErrors));
                break;
            case 2:
                hangMan.setImageResource(R.drawable.game2);
                textAttemptsTicker.setText(String.valueOf(nbErrors));
                break;
            case 3:
                hangMan.setImageResource(R.drawable.game3);
                textAttemptsTicker.setText(String.valueOf(nbErrors));
                break;
            case 4:
                hangMan.setImageResource(R.drawable.game4);
                textAttemptsTicker.setText(String.valueOf(nbErrors));
                break;
            case 5:
                hangMan.setImageResource(R.drawable.game5);
                textAttemptsTicker.setText(String.valueOf(nbErrors));
                break;
            case 6:
                hangMan.setImageResource(R.drawable.game6);
                textAttemptsTicker.setText(String.valueOf(nbErrors));
                break;
            case 7:
                hangMan.setImageResource(R.drawable.game7);
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
        wordBuilder.append(wordHint);
    }



    private void playerGuess() {

        // Kollar så användarinput är 1 bokstav.
        if (userCharInput.length() == 1) {

            userInput = userCharInput.getText().toString().charAt(0); 

            // Kollar om man redan gissat den bokstaven
            if (playerGuesses.contains(userInput)) {
                Toast.makeText(this, "You all ready guessed: " + userInput, Toast.LENGTH_SHORT).show();

                // Om inte, lägg in i playerGuesses
            } else {
                playerGuesses.add(userInput);
                textGuessedLetters.setText("Gissade Bokstäver: " + playerGuesses.toString());
            }

            // Kollar om gissning är rätt
            if (wordToFind.contains(String.valueOf(userInput).toUpperCase())) {

                // Logik för att ersätta bokstaven i "hint" på skärmen för användaren
                for (int i = 0; i < wordToFind.length(); i++) {
                    if (wordToFind.charAt(i) == userInput) {
                        wordBuilder.setCharAt(i, userInput);

                    }
                }

                textWordToFind.setText(wordBuilder.toString());
                Toast.makeText(this, "Correct Guess!", Toast.LENGTH_SHORT).show();
                updateUi();

                // Kollar om man gissat hela ordet rätt
                if (wordBuilder.toString().equals(wordToFind)) {

                    Toast.makeText(this, "YOU WON", Toast.LENGTH_SHORT).show();

                }

                // Om man inte gissat rätt, plussar på en på nbErrors
            } else {
                Toast.makeText(this, "Wrong Guess!", Toast.LENGTH_SHORT).show();
                nbErrors++;
                updateUi();    
            }

        }

        else {
        Toast.makeText(this, "Input a letter!", Toast.LENGTH_SHORT).show();
        }

    }

}
