package com.spellrush.presentation.UI.Components;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.spellrush.R;

//activity for the level complete message display
public class LevelCompleteDisplay extends Activity {
    private final Handler handler = new Handler();
    final int ONE_SECOND = 1000;
    final int DEFAULT_DISPLAY_TIME = 3;
    final int MESSAGE_WIDTH = 700, MESSAGE_HEIGHT = 300;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
}