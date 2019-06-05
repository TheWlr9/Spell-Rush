package com.spellrush.presentation;

import com.spellrush.R;
import com.spellrush.application.Main;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class HomeActivity extends Activity {

    GameView gameView;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.surfaceView = (SurfaceView) findViewById(R.id.gameView);
        this.surfaceHolder = surfaceView.getHolder();

        surfaceHolder.addCallback(gameView);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
