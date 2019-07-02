package com.spellrush.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.spellrush.R;
import com.spellrush.application.ScoreEntry;
import com.spellrush.business.LeaderboardController;
import com.spellrush.services.Services;

public class GameOverActivity extends Activity {

    private LeaderboardController leaderboardController;
    private String playerName;
    private int playerScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);


        Bundle extras = getIntent().getExtras();
        if(extras!=null) {
            playerScore=-1;
            playerScore = extras.getInt("score");
            if(playerScore>=0) {
                String scr = String.valueOf(playerScore);
                TextView playerScore=(TextView) findViewById(R.id.score);
                playerScore.setText(scr);
            }
        }
        createSubmitScoreButton();
        createPlayAgainButton();
        createLeaderBoardButton();
        createExitButton();
        leaderboardController = new LeaderboardController(Services.getLeaderboardPersistence());


}

    public void createSubmitScoreButton(){
        Button submitScoreButton = (Button) findViewById(R.id.Submit_score);
        submitScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText inputText = (EditText) findViewById(R.id.name);
                playerName= inputText.getText().toString();
                leaderboardController.insertScore(new ScoreEntry(playerName, playerScore));

                Intent leaderBoardIntent = new Intent(GameOverActivity.this, LeaderboardActivity.class);
                GameOverActivity.this.startActivity(leaderBoardIntent);
            }
        });
    }

    public void createPlayAgainButton(){
        Button playAgainButton = (Button) findViewById(R.id.PlayAgain);
        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent playAgainIntent = new Intent(GameOverActivity.this, GameActivity.class);
                GameOverActivity.this.startActivity(playAgainIntent);
            }
        });
    }


    public void createExitButton(){
        Button ExitButton = (Button) findViewById(R.id.Exit);
        ExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent exitIntent = new Intent(GameOverActivity.this, HomeActivity.class);
                GameOverActivity.this.startActivity(exitIntent);
            }
        });
    }


    public void createLeaderBoardButton(){
        Button ExitButton = (Button) findViewById(R.id.leaderBoardBtn);
        ExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent leaderBoardIntent = new Intent(GameOverActivity.this, LeaderboardActivity.class);
                GameOverActivity.this.startActivity(leaderBoardIntent);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}