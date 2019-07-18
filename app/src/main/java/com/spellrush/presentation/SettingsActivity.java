package com.spellrush.presentation;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import com.spellrush.R;
public class SettingsActivity extends Activity {
    private AudioManager masterAudioManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        masterAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        initMasterVolumeSeekbar();
    } //end onCreate()

    private void initMasterVolumeSeekbar() {
        try {
            SeekBar masterVolumeSeekbar = (SeekBar) findViewById(R.id.masterVolumeSlider);
            final TextView masterVolumeCurrentText = (TextView) findViewById(R.id.masterVolumeSliderText);

            masterVolumeSeekbar.setMax(masterAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            masterVolumeSeekbar.setProgress(masterAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
            masterVolumeCurrentText.setText("" + masterAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC));

            masterVolumeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onStopTrackingTouch(SeekBar arg0) {}
                @Override
                public void onStartTrackingTouch(SeekBar arg0) {}
                @Override
                public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
                    masterAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                    masterVolumeCurrentText.setText("" + progress);

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}