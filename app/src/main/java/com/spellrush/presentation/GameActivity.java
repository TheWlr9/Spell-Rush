package com.spellrush.presentation;

import android.app.Activity;
import android.os.Bundle;

import com.spellrush.R;
import com.spellrush.audio.AudioManager;
import com.spellrush.audio.SoundEvent;
import com.spellrush.business.GameView;

public class GameActivity extends Activity {
    final static int[] GAME_SOUND_RES_IDS = {R.raw.act05_stage02_loop};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AudioManager.init(getApplicationContext());
        loadSoundsIntoAudioManager();
        setContentView(R.layout.activity_game);
    }

    @Override
    protected void onResume(){
        GameView.getInstance().setPaused(false);
        AudioManager.play(SoundEvent.BATTLE_MUSIC);
        super.onResume();
    }

    @Override
    protected void onStop(){
        GameView.getInstance().setPaused(true);
        AudioManager.pause(SoundEvent.BATTLE_MUSIC);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        AudioManager.stop(SoundEvent.BATTLE_MUSIC);
        AudioManager.release(SoundEvent.BATTLE_MUSIC);
        super.onDestroy();
    }

    private static void loadSoundsIntoAudioManager(){
        AudioManager.addSoundToLib(SoundEvent.BATTLE_MUSIC, GAME_SOUND_RES_IDS[0], true);
    }
}
