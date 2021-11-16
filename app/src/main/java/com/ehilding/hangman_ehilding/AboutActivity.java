package com.ehilding.hangman_ehilding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    final String activityTitle = "Om Spelet";
    TextView txtActivityTitle;
    ImageButton btnPlayAction, btnAboutAction, btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        init();
        setupOnClickers();
    }

    //INITIERAR ALLT I ACTIVITYN
    private void init() {

        txtActivityTitle = (TextView) findViewById(R.id.act_location);
        txtActivityTitle.setText(activityTitle);
        btnPlayAction = (ImageButton) findViewById(R.id.act_play);
        btnAboutAction = (ImageButton) findViewById(R.id.act_info);
        btnPlayAction.setVisibility(View.GONE);
        btnAboutAction.setVisibility(View.GONE);
        btnBack = (ImageButton) findViewById(R.id.act_back);

    }

    //ONCLICK-LISTNERS PÅ ALLA BUTTONS I ACTIVITY
    private void setupOnClickers() {

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AboutActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}