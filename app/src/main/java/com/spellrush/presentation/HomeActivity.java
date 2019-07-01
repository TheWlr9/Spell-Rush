package com.spellrush.presentation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.spellrush.R;
import com.spellrush.audio.AudioManager;
import com.spellrush.audio.SoundEvent;
import com.spellrush.services.Services;

import java.sql.SQLOutput;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

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
        copyDatabaseToDevice();
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

    // FROM SAMPLE PROJECT - Copies database to the device
    private void copyDatabaseToDevice() {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try {
            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);

            Services.setDBPathName(dataDirectory.toString() + "/" + Services.getDBPathName());

            System.out.println("Database Path: " + Services.getDBPathName());

        } catch (final IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    // FROM SAMPLE PROJECT - goes through each database asset and copies into device
    private void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];

            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }
}
