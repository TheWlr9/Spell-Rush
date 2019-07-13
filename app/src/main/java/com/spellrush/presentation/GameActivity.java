package com.spellrush.presentation;

import android.app.Activity;
import android.os.Bundle;

import com.spellrush.R;
import com.spellrush.audio.AudioManager;
import com.spellrush.audio.AudioManagerError;
import com.spellrush.audio.SoundEvent;
import com.spellrush.business.GameView;

public class GameActivity extends Activity {
    final static int[] GAME_SOUND_RES_IDS = {R.raw.act05_stage02_loop};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupMyAudio();
        setContentView(R.layout.activity_game);
    }

    private void setupMyAudio(){
        AudioManager.init(getApplicationContext());
        loadSoundsIntoAudioManager();
    }

    @Override
    protected void onResume(){
        GameView.getInstance().setPaused(false);

        try {
            AudioManager.play(SoundEvent.BATTLE_MUSIC);
        }
        catch (AudioManagerError ame){
            System.err.println(ame);

            setupMyAudio();
        }

        super.onResume();
    }

    @Override
    protected void onStop(){
        GameView.getInstance().setPaused(true);

        try {
            AudioManager.pause(SoundEvent.BATTLE_MUSIC);
        }
        catch (AudioManagerError ame){
            System.err.println(ame);
        }

        super.onStop();
    }

    @Override
    protected void onDestroy() {
        try {
            AudioManager.stop(SoundEvent.BATTLE_MUSIC);
            AudioManager.release(SoundEvent.BATTLE_MUSIC);
        }
        catch(AudioManagerError ame){
            System.err.println(ame);
        }

        super.onDestroy();
    }

    private void loadSoundsIntoAudioManager(){
        try {
            AudioManager.addSoundToLib(SoundEvent.BATTLE_MUSIC, GAME_SOUND_RES_IDS[0], true);
        }
        catch (AudioManagerError ame){
            System.err.println(ame);
        }
    }
}
