package com.ehilding.hangman_ehilding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnPlay, btnAbout;
    ImageButton btnPlayAction, btnAboutAction;
    TextView txtActivityTitle;
    final String activityTitle = "Huvudmeny";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         init();
         initOnClickers();

    }

    private void init() {
        btnPlay = (Button) findViewById(R.id.btn_play_game_main);
        btnAbout = (Button) findViewById(R.id.btn_about_game_main);
        txtActivityTitle = (TextView) findViewById(R.id.act_location);
        txtActivityTitle.setText(activityTitle);
        btnPlayAction = (ImageButton) findViewById(R.id.act_play);
        btnAboutAction = (ImageButton) findViewById(R.id.act_info);

    }

    private void initOnClickers() {

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

    private void goToPlay() {

        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(intent);

    }

    private void goToAbout() {

        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(intent);

    }
}