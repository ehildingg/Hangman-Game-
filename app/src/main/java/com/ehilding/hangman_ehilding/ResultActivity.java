package com.ehilding.hangman_ehilding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView tx = findViewById(R.id.textView3);

        Intent incomingIntent = getIntent();
        String result = incomingIntent.getStringExtra("resultat");
        String ord = incomingIntent.getStringExtra("ord");
        int triesLeft = incomingIntent.getIntExtra("tries", 0);
        tx.setText(result + ord + triesLeft);

    }
}