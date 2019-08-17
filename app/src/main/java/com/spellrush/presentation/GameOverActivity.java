package com.spellrush.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.spellrush.R;
import com.spellrush.application.ScoreEntry;
import com.spellrush.audio.AudioManager;
import com.spellrush.audio.AudioManagerError;
import com.spellrush.audio.SoundEvent;
import com.spellrush.business.GameVolumeSettings;
import com.spellrush.business.LeaderboardController;
import com.spellrush.services.Services;

public class GameOverActivity extends Activity {

    final static int[] GAME_OVER_SOUND_RES_IDS = {R.raw.sanctuary};

    private LeaderboardController leaderboardController;
    private String playerName;
    private int playerScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupMyAudio();

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

    @Override
    protected void onResume(){
        super.onResume();

        try{
            AudioManager.play(SoundEvent.GAME_OVER_MUSIC, false);
            AudioManager.setVolume(SoundEvent.GAME_OVER_MUSIC, GameVolumeSettings.getMusicVolume());
        }
        catch(AudioManagerError ame){
            System.err.println("Error with linking GAME_OVER_MUSIC to file");
            setupMyAudio();
        }
    }

    private void setupMyAudio(){
        AudioManager.init(getApplicationContext());
        loadSoundsIntoAudioManager();
    }

    public void createSubmitScoreButton(){
        Button submitScoreButton = (Button) findViewById(R.id.Submit_score);
        submitScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText inputText = (EditText) findViewById(R.id.name);
                playerName= inputText.getText().toString();

                if(!playerName.isEmpty()) {
                    leaderboardController.insertScore(new ScoreEntry(playerName, playerScore));

                    Intent leaderBoardIntent = new Intent(GameOverActivity.this, LeaderboardActivity.class);
                    GameOverActivity.this.startActivity(leaderBoardIntent);
                }
                else{
                    CharSequence text = "Please enter a name";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                    toast.show();
                }
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

    private void loadSoundsIntoAudioManager(){
        try {
            AudioManager.addSoundToLib(SoundEvent.GAME_OVER_MUSIC, GAME_OVER_SOUND_RES_IDS[0], true);
            AudioManager.setVolume(SoundEvent.GAME_OVER_MUSIC, GameVolumeSettings.getMusicVolume());
        }
        catch (AudioManagerError ame){
            System.err.println(ame);
        }
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(GameOverActivity.this, HomeActivity.class));
        finish();
    }


    @Override
    protected void onStop() {
        try {
            AudioManager.stop(SoundEvent.GAME_OVER_MUSIC);
        }
        catch(AudioManagerError ame){
            System.err.println("Stopping non-linked GAME_OVER_SCREEN; proceeding as normal.");
        }

        super.onStop();
    }

    @Override
    protected void onDestroy() {
        try {
            AudioManager.stop(SoundEvent.GAME_OVER_MUSIC);
            AudioManager.release(SoundEvent.GAME_OVER_MUSIC);
        }
        catch(AudioManagerError ame){
            System.err.println("Error in destroying GAME_OVER_MUSIC: Unlinked from file");
        }

        super.onDestroy();
    }
}