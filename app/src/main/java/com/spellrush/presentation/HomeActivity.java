package com.spellrush.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.spellrush.R;
import com.spellrush.audio.AudioManager;
import com.spellrush.audio.AudioManagerError;
import com.spellrush.audio.SoundEvent;
import com.spellrush.business.GameVolumeSettings;
import com.spellrush.business.LevelManager.LevelManager;
import com.spellrush.persistence.utils.DBHelper;
import com.spellrush.presentation.UI.Components.LevelStartDisplay;

public class HomeActivity extends Activity {
    final static int[] HOME_SOUND_RES_IDS = {R.raw.lines_of_code};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setupMyAudio();
        createStartButton();
        createSettingsButton();
        createLeaderboardButton();
        createTutorialButton();
        DBHelper.copyDatabaseToDevice(getApplicationContext());
    }

    private void createTutorialButton() {
        Button tutButton = (Button) findViewById(R.id.tutBtn);
        tutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tutorialIntent = new Intent(HomeActivity.this, TutorialActivity.class);
                HomeActivity.this.startActivity((tutorialIntent));
            }
        });
    }

    private void setupMyAudio(){
        AudioManager.init(getApplicationContext());
        loadSoundsIntoAudioManager();
    }

    @Override
    protected void onResume(){
        try {
            AudioManager.play(SoundEvent.TITLE_MUSIC, false);
            AudioManager.setVolume(SoundEvent.TITLE_MUSIC, GameVolumeSettings.getMusicVolume());
        }
        catch(AudioManagerError ame){
            System.err.println(ame);

            setupMyAudio();
        }

        super.onResume();
    }


    public void createStartButton(){
        Button startButton = (Button) findViewById(R.id.startBtn);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent levelStartDisplay = new Intent(HomeActivity.this, LevelStartDisplay.class);
                if(LevelManager.getInstance() != null) {
                    String level = LevelManager.getInstance().getCurrentLevel();
                    levelStartDisplay.putExtra("levelName", level);
                }
                // display the start level message, then once its complete, start the game
                startActivityForResult(levelStartDisplay, 1);

            }
        });
    }

    public void createSettingsButton(){
        Button settingsButton = (Button) findViewById(R.id.settingsBtn);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settingsIntent = new Intent(HomeActivity.this, SettingsActivity.class);
                HomeActivity.this.startActivity((settingsIntent));
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

    private void loadSoundsIntoAudioManager(){
        try {
            AudioManager.addSoundToLib(SoundEvent.TITLE_MUSIC, HOME_SOUND_RES_IDS[0], true);
            AudioManager.setVolume(SoundEvent.TITLE_MUSIC, GameVolumeSettings.getMusicVolume());
        }
        catch(AudioManagerError ame){
            System.err.println(ame);
        }
    }

    @Override
    protected void onStop(){
        try {
            AudioManager.pause(SoundEvent.TITLE_MUSIC);
        }
        catch(AudioManagerError ame){
            System.err.println(ame);

            setupMyAudio();
        }

        super.onStop();
    }

    @Override
    protected void onDestroy() {
        try {
            AudioManager.stop(SoundEvent.TITLE_MUSIC);
            AudioManager.release(SoundEvent.TITLE_MUSIC);
        }
        catch(AudioManagerError ame){
            System.err.println(ame);
        }

        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        // Ensure it does nothing...
    }

    // start the game activity once the display intent is complete
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Intent gameIntent = new Intent(HomeActivity.this, GameActivity.class);
        HomeActivity.this.startActivity(gameIntent);
    }
}
