package com.spellrush.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.spellrush.R;
import com.spellrush.audio.AudioManager;
import com.spellrush.audio.SoundEvent;

public class GameActivity extends Activity {
    final static int[] GAME_SOUND_RES_IDS = {R.raw.act05_stage02_loop};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AudioManager.init(getApplicationContext());
        loadSoundsIntoAudioManager();
        setContentView(R.layout.activity_game);
        createMenuButton();
    }

    @Override
    protected void onResume(){
        AudioManager.play(SoundEvent.BATTLE_MUSIC);
        super.onResume();
    }

    @Override
    protected void onStop(){
        AudioManager.pause(SoundEvent.BATTLE_MUSIC);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        AudioManager.stop(SoundEvent.BATTLE_MUSIC);
        AudioManager.release(SoundEvent.BATTLE_MUSIC);
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        // Ensure it does nothing...
    }

    private void createMenuButton(){
        Button startButton = (Button) findViewById(R.id.menuBtn);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menuIntent = new Intent(GameActivity.this, HomeActivity.class);
                GameActivity.this.startActivity(menuIntent);
            }
        });
    }

    private static void loadSoundsIntoAudioManager(){
        AudioManager.addSoundToLib(SoundEvent.BATTLE_MUSIC, GAME_SOUND_RES_IDS[0], true);
    }
}
