package com.ehilding.hangman_ehilding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    final String activityTitle = "Resultat";
    TextView txtResult, txtWordToGuess, txtTriesLeft;
    ImageButton btnPlayAction, btnAboutAction, btnBack;
    TextView txtActivityTitle;
    Button backToMain;
    String result, wordToFind;
    int triesLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        init();
        setupOnClickers();
        getResultIntent();
        displayResult();
    }

    //INITIERAR ALLT I ACTIVITYN
    public void init () {

        txtResult = (TextView) findViewById(R.id.txt_result);
        txtTriesLeft = (TextView) findViewById(R.id.txt_tries_left);
        txtWordToGuess = (TextView) findViewById(R.id.txt_word_to_guess);
        backToMain = (Button) findViewById(R.id.btn_back_to_main);
        txtActivityTitle = (TextView) findViewById(R.id.act_location);
        txtActivityTitle.setText(activityTitle);
        btnPlayAction = (ImageButton) findViewById(R.id.act_play);
        btnAboutAction = (ImageButton) findViewById(R.id.act_info);
        btnBack = (ImageButton) findViewById(R.id.act_back);


    }

    //ONCLICK-LISTNERS PÅ ALLA BUTTONS I ACTIVITY
    private void setupOnClickers() {

        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        btnPlayAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPlay();
            }
        });

        btnAboutAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAbout();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

    }

    // HÄMTAR INFO FRÅN INTENT (OM MAN VUNNIT ELLER FÖRLORAT)
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

    // VISAR INFO FRÅN INTENT
    private void displayResult() {

        if (result != null && wordToFind != null) {

            txtResult.setText(result);
            txtWordToGuess.setText("Landet va: " + wordToFind);

            if (triesLeft != 0 ) {
                txtTriesLeft.setText("Du hade " + triesLeft + " försök kvar!");
            } else {
                txtTriesLeft.setVisibility(View.GONE);
            }



        } else {

            txtResult.setText("Något fel med intent");
            txtTriesLeft.setText("Något fel med intent");
            txtWordToGuess.setText("Något fel med intent");

        }






    }

    // ONCLICK-METOD
    private void goToPlay() {

        Intent intent = new Intent(ResultActivity.this, GameActivity.class);
        startActivity(intent);

    }

    // ONCLICK-METOD
    private void goToAbout() {

        Intent intent = new Intent(ResultActivity.this, AboutActivity.class);
        startActivity(intent);

    }
}
