package com.spellrush.presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import com.spellrush.R;
import com.spellrush.business.GameVolumeSettings;

public class SettingsActivity extends Activity {
    private AudioManager masterAudioManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        masterAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        initMasterVolumeSeekbar();
        initMusicVolumeSeekbar();
        initSFXVolumeSeekbar();
    } //end onCreate()

    private void initMasterVolumeSeekbar() {
        try {
            SeekBar masterVolumeSeekbar = (SeekBar) findViewById(R.id.masterVolumeSlider);
            final TextView masterVolumeCurrentText = (TextView) findViewById(R.id.masterVolumeSliderText);

            masterVolumeSeekbar.setMax(masterAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));

            // set seekbar to current volume setting
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
                    System.out.println("Master Volume set to :" + progress);
                }
            });
        } catch (Exception e) {
            displayException(e);
        }
    } //end initMasterVolumeSeekbar

    private void initMusicVolumeSeekbar() {
        try {
            SeekBar musicVolumeSeekbar = (SeekBar) findViewById(R.id.musicVolumeSlider);
            final TextView musicVolumeCurrentText = (TextView) findViewById(R.id.musicVolumeSliderText);

            musicVolumeSeekbar.setMax((int)GameVolumeSettings.MAXIMUM_VOLUME);

            // set seekbar to current volume setting
            musicVolumeSeekbar.setProgress((int)GameVolumeSettings.getMusicVolume());
            musicVolumeCurrentText.setText("" + (int)GameVolumeSettings.getMusicVolume());

            musicVolumeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onStopTrackingTouch(SeekBar arg0) {}
                @Override
                public void onStartTrackingTouch(SeekBar arg0) {}
                @Override
                public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
                    GameVolumeSettings.setMusicVolume(progress);
                    System.out.println("Music Volume set to :" + progress);
                    musicVolumeCurrentText.setText("" + progress);

                }
            });
        } catch (Exception e) {
            displayException(e);
        }
    } //end initMusicVolumeSeekbar

    private void initSFXVolumeSeekbar() {
        try {
            SeekBar sfxVolumeSeekbar = (SeekBar) findViewById(R.id.soundEffectsVolumeSlider);
            final TextView sfxVolumeCurrentText = (TextView) findViewById(R.id.soundEffectsVolumeSliderText);

            sfxVolumeSeekbar.setMax((int)GameVolumeSettings.MAXIMUM_VOLUME);

            // set seekbar to current volume setting
            sfxVolumeSeekbar.setProgress((int)GameVolumeSettings.getSfxVolume());
            sfxVolumeCurrentText.setText("" + (int)GameVolumeSettings.getSfxVolume());

            sfxVolumeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onStopTrackingTouch(SeekBar arg0) {}
                @Override
                public void onStartTrackingTouch(SeekBar arg0) {}
                @Override
                public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
                    GameVolumeSettings.setSfxVolume(progress);
                    System.out.println("SFX Volume set to :" + progress);
                    sfxVolumeCurrentText.setText("" + progress);

                }
            });
        } catch (Exception e) {
            displayException(e);
        }
    } //end initSFXVolumeSeekbar

    private void displayException(Exception e) {
        AlertDialog.Builder message = new AlertDialog.Builder(this);
        message.setTitle("Something Went Wrong!");
        message.setMessage(e.getMessage());
        message.setCancelable(false);
        message.setNeutralButton("Made an oopsie", null);
        message.show();
    } //end displayException(e)
}