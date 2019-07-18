package com.spellrush.presentation.UI.Components;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.spellrush.R;
import com.spellrush.audio.AudioManager;
import com.spellrush.audio.AudioManagerError;
import com.spellrush.audio.SoundEvent;

//activity for the level complete message display
public class LevelCompleteDisplay extends Activity {
    private final Handler handler = new Handler();
    final int ONE_SECOND = 1000;
    final int DEFAULT_DISPLAY_TIME = 3;
    final int MESSAGE_WIDTH = 700, MESSAGE_HEIGHT = 300;
    final static int[] LEVEL_COMPLETE_SOUND_RES_IDS = {R.raw.victory};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupMyAudio();

        //display time is set to default
        int displayTime = DEFAULT_DISPLAY_TIME;

        //set width and height of message display
        getWindow().setLayout(MESSAGE_WIDTH, MESSAGE_HEIGHT);
        setContentView(R.layout.activity_level_complete);

        Bundle extras = getIntent().getExtras();
        if(extras!=null) {
            // change display time length based on what is sent to the activity
            if(extras.getInt("displayTime") > 0)
                displayTime = extras.getInt("displayTime");
        }
        handler.postDelayed(new Runnable() {
            public void run() {
                finish();
            }
        }, displayTime * ONE_SECOND);
    }

    @Override
    public void onResume(){
        super.onResume();

        try {
            AudioManager.play(SoundEvent.LEVEL_COMPLETE_JINGLE, false);
        }
        catch(AudioManagerError ame){
            System.err.println("Error in linking LEVEL_COMPLETE_JINGLE to file");

            setupMyAudio();
        }
    }

    @Override
    public void onStop(){
        try{
            AudioManager.stop(SoundEvent.LEVEL_COMPLETE_JINGLE);
        }
        catch(AudioManagerError ame){
            System.err.println("Stopping non-linked LEVEL_COMPLETE_JINGLE, proceeding as normal.");
        }

        super.onStop();
    }

    @Override
    public void onDestroy(){
        try{
            AudioManager.stop(SoundEvent.LEVEL_COMPLETE_JINGLE);
            AudioManager.release(SoundEvent.LEVEL_COMPLETE_JINGLE);
        }
        catch(AudioManagerError ame){
            System.err.println("Error in destroying LEVEL_COMPLETE_JINGLE: Unlinked from file");
        }

        super.onDestroy();
    }

    private void setupMyAudio(){
        AudioManager.init(getApplicationContext());
        loadSoundsIntoAudioManager();
    }

    private void loadSoundsIntoAudioManager(){
        try {
            AudioManager.addSoundToLib(SoundEvent.LEVEL_COMPLETE_JINGLE, LEVEL_COMPLETE_SOUND_RES_IDS[0], false);
        }
        catch (AudioManagerError ame){
            System.err.println(ame);
        }
    }
}