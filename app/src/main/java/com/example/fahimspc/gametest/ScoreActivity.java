package com.example.fahimspc.gametest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    TextView scorerText;
    TextView scoreText;
    ScorePreference scorePreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        getSupportActionBar().hide();
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        scorerText = (TextView) findViewById(R.id.scorerNameText);
        scoreText = (TextView) findViewById(R.id.scoreText);

        scorePreference = new ScorePreference(this);
        String score;
        if(scorePreference.getBooleanPreferences(ScorePreference.IS_HIGH_SCORE_SET)){
            scorerText.setText(scorePreference.getStringPreferences(ScorePreference.HIGH_SCORER));
            score = Integer.toString(scorePreference.getIntegerPreferences(ScorePreference.HIGH_SCORE));
            scoreText.setText(score);
            scorerText.setVisibility(View.GONE);

        } else {
            scorerText.setText(R.string.no_scorer_string);
            score = "0";
            scoreText.setText(score);
            scoreText.setVisibility(View.GONE);
        }

    }
}
