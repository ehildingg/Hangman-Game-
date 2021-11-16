package com.ehilding.hangman_ehilding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView txtResult, txtWordToGuess, txtTriesLeft;
    Button backToMain;
    String result, wordToFind;
    int triesLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        init();
        getResultIntent();
        displayResult();



    }


    public void init () {

        txtResult = (TextView) findViewById(R.id.txt_result);
        txtTriesLeft = (TextView) findViewById(R.id.txt_tries_left);
        txtWordToGuess = (TextView) findViewById(R.id.txt_word_to_guess);
        backToMain = (Button) findViewById(R.id.btn_back_to_main);

        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

    }

    private void getResultIntent() {

        Bundle extras = getIntent().getExtras();
        String resultFromIntent;
        String wordFromIntent;
        int triesFromIntent;

        if (extras != null) {
            resultFromIntent = extras.getString("resultat");
            wordFromIntent = extras.getString("ord");
            triesFromIntent = extras.getInt("tries");

            result = resultFromIntent;
            wordToFind = wordFromIntent;
            triesLeft = triesFromIntent;
        } else {
            Log.d("Tag_01", "Extras va null");
        }





    }

    private void displayResult() {

        if (result != null && wordToFind != null) {

            txtResult.setText(result);
            txtWordToGuess.setText("Landet var: " + wordToFind);
            txtTriesLeft.setText("Du hade: " + triesLeft + "försök kvar!");


        } else {

            txtResult.setText("taco");
            txtTriesLeft.setText("yooyo");
            txtWordToGuess.setText("hehe");

        }






    }

}