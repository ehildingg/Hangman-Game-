package com.ehilding.hangman_ehilding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    final String activityTitle = "Hangman!";
    ImageView hangMan;
    TextView textAttempts, textAttemptsTicker, textWordToFind, textGuessedLetters, txtActivityTitle;
    EditText userCharInput;
    Button buttonGuess, buttonNewGame;
    ImageButton btnPlayAction, btnAboutAction, btnBack;
    GameWords game = GameWords.getInstance();
    public char userInput;
    public ArrayList<Character> playerGuesses = new ArrayList<>();
    public static String wordToFind;
    public static String wordHint;
    public int nbErrors = 0;
    public int attemptsLeft = game.MAX_ERRORS;
    StringBuilder wordBuilder = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        init();
        setupOnClickers();
    }

    //INITIERAR ALLT I ACTIVITYN
    private void init() {
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
        btnPlayAction = (ImageButton) findViewById(R.id.act_play);
        btnAboutAction = (ImageButton) findViewById(R.id.act_info);
        btnPlayAction.setVisibility(View.GONE);
        txtActivityTitle = (TextView) findViewById(R.id.act_location);
        txtActivityTitle.setText(activityTitle);
        btnBack = (ImageButton) findViewById(R.id.act_back);
        updateUi();

    }

    //ONCLICK-LISTNERS PÅ ALLA BUTTONS I ACTIVITY
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

        btnAboutAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GameActivity.this, AboutActivity.class);
                startActivity(i);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GameActivity.this, MainActivity.class);
                startActivity(i);
            }
        });



    }

    //UPPDATERAR HANGMAN-GUBBE UTIFRÅN ANTAL ERRORS + UPPDATERAR "FÖRSÖK KVAR"
    private void updateUi() {
        switch (nbErrors) {
            /*case 1:
                hangMan.setImageResource(0);
                textAttemptsTicker.setText(String.valueOf(nbErrors));
                break;*/
            case 0:
                hangMan.setImageResource(R.drawable.game0);
                textAttemptsTicker.setText(String.valueOf(attemptsLeft));
                break;
            case 1:
                hangMan.setImageResource(R.drawable.game1);
                textAttemptsTicker.setText(String.valueOf(attemptsLeft));
                break;
            case 2:
                hangMan.setImageResource(R.drawable.game2);
                textAttemptsTicker.setText(String.valueOf(attemptsLeft));
                break;
            case 3:
                hangMan.setImageResource(R.drawable.game3);
                textAttemptsTicker.setText(String.valueOf(attemptsLeft));
                break;
            case 4:
                hangMan.setImageResource(R.drawable.game4);
                textAttemptsTicker.setText(String.valueOf(attemptsLeft));
                break;
            case 5:
                hangMan.setImageResource(R.drawable.game5);
                textAttemptsTicker.setText(String.valueOf(attemptsLeft));
                break;
            case 6:
                hangMan.setImageResource(R.drawable.game6);
                textAttemptsTicker.setText(String.valueOf(attemptsLeft));
                break;
            case 7:
                hangMan.setImageResource(R.drawable.game7);
                textAttemptsTicker.setText(String.valueOf(attemptsLeft));

        }
    }

    // NYTT SLUMPAT ORD, SAMT RÄTT "HINT" (----)
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

    // HANTERAR USERINPUT/GISSNING
    private void playerGuess() {

        // FELHANTERING, INPUT MÅSTE VARA 1 TECKEN.
        if (userCharInput.length() == 1) {

            userInput = userCharInput.getText().toString().charAt(0); 

            // OM SPELARE REDAN GISSAT BOKSTAVEN
            if (playerGuesses.contains(userInput)) {
                Toast.makeText(this, "You all ready guessed: " + userInput, Toast.LENGTH_SHORT).show();

                // OM DET ÄR NY GISSNING, LÄGG IN I CHAR-ARRAYLIST SOM VISAS
            } else {
                playerGuesses.add(userInput);
                textGuessedLetters.setText("Gissade Bokstäver: " + playerGuesses.toString());
            }

            // VID RÄTT GISSNING
            if (wordToFind.contains(String.valueOf(userInput).toUpperCase())) {
                correctGuess();
                // OM GISSNING ÄR FEL
             } else {
                wrongGuess();
            }
        }
        // OM ANVÄNDAREN INTE ANGETT NÅGOT
        else {
        Toast.makeText(this, "Input a letter!", Toast.LENGTH_SHORT).show();
        }

    }

    // OM GISSNING ÄR FEL
    private void wrongGuess() {

        Toast.makeText(this, "Wrong Guess!", Toast.LENGTH_SHORT).show();
        nbErrors++;
        attemptsLeft--;
        updateUi();

        if (nbErrors == game.MAX_ERRORS) {

            Intent i = new Intent(this, ResultActivity.class);
            i.putExtra("resultat", "Du förlora!");
            i.putExtra("ord", wordToFind);
            i.putExtra("tries", attemptsLeft);
            startActivity(i);

        }
    }

    // OM GISSNING ÄR RÄTT
    private void correctGuess() {

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

            Intent i = new Intent(this, ResultActivity.class);
            i.putExtra("resultat", "Du vann!");
            i.putExtra("ord", wordToFind);
            i.putExtra("tries", attemptsLeft);
            startActivity(i);
        }
    }
}
