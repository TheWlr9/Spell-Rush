package com.spellrush.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.spellrush.R;
import com.spellrush.audio.AudioManager;
import com.spellrush.audio.AudioManagerError;
import com.spellrush.audio.SoundEvent;

public class TutorialActivity extends Activity {
    final static int[] TUT_SOUND_RES_IDS = {R.raw.lines_of_code};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        setupMyAudio();

        ViewPager viewPager = (ViewPager) findViewById(R.id.tutorial);
        TutorialAdapter adapter= new TutorialAdapter(this);
        viewPager.setAdapter(adapter);
        try {
            AudioManager.play(SoundEvent.TITLE_MUSIC);
        } catch (AudioManagerError ame) {
            System.err.println(ame);
        }
    }

    private void setupMyAudio(){
        AudioManager.init(getApplicationContext());
        loadSoundsIntoAudioManager();
    }

    @Override
    protected void onResume(){
        try {
            AudioManager.play(SoundEvent.TITLE_MUSIC);
        }
        catch(AudioManagerError ame){
            System.err.println(ame);

            setupMyAudio();
        }

        super.onResume();
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

    private void loadSoundsIntoAudioManager(){
        try {
            AudioManager.addSoundToLib(SoundEvent.TITLE_MUSIC, TUT_SOUND_RES_IDS[0], true);
        }
        catch (AudioManagerError ame){
            System.err.println(ame);
        }
    }
}
