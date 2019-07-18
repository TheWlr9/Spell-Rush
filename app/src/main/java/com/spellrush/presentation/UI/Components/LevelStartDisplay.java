package com.spellrush.presentation.UI.Components;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.spellrush.R;

//activity for the level start message display
public class LevelStartDisplay extends Activity {
    private final Handler handler = new Handler();
    final int ONE_SECOND = 1000;
    final int DEFAULT_DISPLAY_TIME = 3;
    final int MESSAGE_WIDTH = 700, MESSAGE_HEIGHT = 300;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //display time is set to default
        int displayTime = DEFAULT_DISPLAY_TIME;
        String levelName = null;

        //set width and height of message display
        getWindow().setLayout(MESSAGE_WIDTH, MESSAGE_HEIGHT);
        setContentView(R.layout.activity_level_start);
        Bundle extras = getIntent().getExtras();
        if(extras!=null) {
            // change display time length based on what is sent to the activity
            if(extras.getInt("displayTime") > 0)
                displayTime = extras.getInt("displayTime");

            if(extras.getString("levelName") != null)
                levelName = extras.getString("levelName");

            if(levelName != null) {
                TextView level = (TextView)findViewById(R.id.level_name);
                level.setText(levelName);
            }
        }
        handler.postDelayed(new Runnable() {
            public void run() {
                finish();
            }
        }, displayTime * ONE_SECOND);

    }
}