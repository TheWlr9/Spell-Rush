package com.spellrush.presentation.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import com.spellrush.buisness.PlayerController;
import com.spellrush.presentation.Views.Components.HealthBar;

public abstract class GameViewLayer extends SurfaceView implements SurfaceHolder.Callback {

    public GameViewLayer(Context context){
        super(context);
        getHolder().addCallback(this);
        setFocusable(true);
    } // end constructor method

    public abstract void update();

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
    } // end draw()

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }
}
