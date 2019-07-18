package com.spellrush.presentation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.widget.TextView;

import com.spellrush.R;
import com.spellrush.audio.AudioManager;
import com.spellrush.audio.AudioManagerError;
import com.spellrush.audio.SoundEvent;
import com.spellrush.business.GameView;
import com.spellrush.business.GameVolumeSettings;
import com.spellrush.business.LevelManager.LevelManager;
import com.spellrush.presentation.UI.Components.LevelCompleteDisplay;
import com.spellrush.presentation.UI.Components.LevelStartDisplay;

public class GameActivity extends Activity {

    //Must be updated and kept in sync with below array(s)
    final static int NUM_LOOPING_SOUNDS = 1;
    //These two arrays must be parallel
    //Looping sounds must be ordered before non-looping sounds
    final static SoundEvent[] GAME_SOUND_SOUNDEVENTS = {SoundEvent.BATTLE_MUSIC, SoundEvent.SPAWN_FIRE,
    SoundEvent.SPAWN_WATER, SoundEvent.SPAWN_GRASS, SoundEvent.PLAYER_DAMAGED, SoundEvent.ENEMY_DAMAGED,
    SoundEvent.SPELLS_COLLIDED};
    final static int[] GAME_SOUND_RES_IDS = {R.raw.act05_stage02_loop, R.raw.fire_attack,
    R.raw.water_attack, R.raw.ground_attack, R.raw.hit_player, R.raw.hit_enemy, R.raw.spell_collision};

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

    // display the start level message
    public static void displayStartLevel(Context context, int displayTime) {
        if(LevelManager.getInstance() != null){
            String level = LevelManager.getInstance().getCurrentLevel();
            Intent levelStartDisplayIntent = new Intent(context, LevelStartDisplay.class);
            levelStartDisplayIntent.putExtra("displayTime", displayTime);
            levelStartDisplayIntent.putExtra("levelName", level);
            context.startActivity(levelStartDisplayIntent);
        }
    }

    // display the level complete message
    public static void displayLevelComplete(Context context, int displayTime) {
        try{
            AudioManager.stop(SoundEvent.BATTLE_MUSIC);
        }
        catch(AudioManagerError ame){
            System.err.println("Stopping non-linked BATTLE_MUSIC, proceeding as normal.");
        }

        Intent levelCompleteDisplayIntent = new Intent(context, LevelCompleteDisplay.class);
        levelCompleteDisplayIntent.putExtra("displayTime", displayTime);
        context.startActivity(levelCompleteDisplayIntent);
    }

    @Override
    protected void onResume(){
        GameView.getInstance().setPaused(false);

        try {
            AudioManager.play(SoundEvent.BATTLE_MUSIC, false);
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
            //Stop all
            for(int i = 0; i < GAME_SOUND_SOUNDEVENTS.length; i++) {
                AudioManager.stop(GAME_SOUND_SOUNDEVENTS[i]);
            }
            //Release all
            for (int i = 0; i < GAME_SOUND_SOUNDEVENTS.length; i++){
                AudioManager.release(GAME_SOUND_SOUNDEVENTS[i]);
            }
        }
        catch(AudioManagerError ame){
            System.err.println(ame);
        }

        super.onDestroy();
    }

    @Override
    public void onBackPressed(){
        LevelManager.getInstance().reset();

        super.onBackPressed();
    }

    private void loadSoundsIntoAudioManager(){
        try {
            int i = 0;

            //For looping sounds
            for (; i < NUM_LOOPING_SOUNDS; i++){
                AudioManager.addSoundToLib(GAME_SOUND_SOUNDEVENTS[0], GAME_SOUND_RES_IDS[i], true);
                AudioManager.setVolume(GAME_SOUND_SOUNDEVENTS[i], GameVolumeSettings.getMusicVolume());
            }
            //For non-looping sounds
            for(; i < GAME_SOUND_SOUNDEVENTS.length; i++){
                AudioManager.addSoundToLib(GAME_SOUND_SOUNDEVENTS[i], GAME_SOUND_RES_IDS[i], false);
                AudioManager.setVolume(GAME_SOUND_SOUNDEVENTS[i], GameVolumeSettings.getSfxVolume());
            }
        }
        catch (AudioManagerError ame){
            System.err.println(ame);
        }
    }
}
