package com.spellrush.presentation;

import android.app.Activity;
import android.os.Bundle;

import com.spellrush.buisness.GameView;

public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
