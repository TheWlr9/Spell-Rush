package com.spellrush.presentation;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

public class HomeActivity extends Activity {

    GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameView = new GameView(this);
        gameView.setBackgroundColor(Color.DKGRAY);

        setContentView(gameView);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
