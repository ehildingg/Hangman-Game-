package com.ehilding.hangman_ehilding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final String activityTitle = "Huvudmeny";
    Button btnPlay, btnAbout;
    ImageButton btnPlayAction, btnAboutAction, btnBack;
    TextView txtActivityTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setupOnClickers();
    }

    //INITIERAR ALLT I ACTIVITYN
    private void init() {
        btnPlay = (Button) findViewById(R.id.btn_play_game_main);
        btnAbout = (Button) findViewById(R.id.btn_about_game_main);
        txtActivityTitle = (TextView) findViewById(R.id.act_location);
        txtActivityTitle.setText(activityTitle);
        btnPlayAction = (ImageButton) findViewById(R.id.act_play);
        btnAboutAction = (ImageButton) findViewById(R.id.act_info);
        btnBack = (ImageButton) findViewById(R.id.act_back);
        btnBack.setVisibility(View.GONE);

    }

    //ONCLICK-LISTNERS PÅ ALLA BUTTONS I ACTIVITY
    private void setupOnClickers() {

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goToPlay();

            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAbout();
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


    }

    // ONCLICK-METOD
    private void goToPlay() {

        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(intent);

    }

    // ONCLICK-METOD
    private void goToAbout() {

        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(intent);

    }
}