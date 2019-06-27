package com.spellrush.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.spellrush.R;
import com.spellrush.audio.AudioManager;
import com.spellrush.audio.SoundEvent;

import java.sql.SQLOutput;

public class HomeActivity extends Activity {
    final static int[] HOME_SOUND_RES_IDS = {R.raw.lines_of_code};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        AudioManager.init(getApplicationContext());
        loadSoundsIntoAudioManager();
        createStartButton();
        //createSettingsButton();
        createLeaderboardButton();

    }

    @Override
    protected void onResume(){
        AudioManager.play(SoundEvent.TITLE_MUSIC);
        super.onResume();
    }


    public void createStartButton(){
        Button startButton = (Button) findViewById(R.id.startBtn);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameIntent = new Intent(HomeActivity.this, GameActivity.class);
                HomeActivity.this.startActivity(gameIntent);
            }
        });
    }

    public void createSettingsButton(){
        Button settingsButton = (Button) findViewById(R.id.settingsBtn);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Settings Button clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void createLeaderboardButton(){
        Button leaderBoardButton = (Button) findViewById(R.id.leaderBoardBtn);
        leaderBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent leaderboardIntent = new Intent(HomeActivity.this, LeaderboardActivity.class);
                HomeActivity.this.startActivity((leaderboardIntent));
            }
        });
    }

    private static void loadSoundsIntoAudioManager(){
        AudioManager.addSoundToLib(SoundEvent.TITLE_MUSIC, HOME_SOUND_RES_IDS[0], true);
    }

    @Override
    protected void onStop(){
        AudioManager.pause(SoundEvent.TITLE_MUSIC);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        AudioManager.stop(SoundEvent.TITLE_MUSIC);
        AudioManager.release(SoundEvent.TITLE_MUSIC);
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        // Ensure it does nothing...
    }
}
