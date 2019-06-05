package com.spellrush.presentation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.spellrush.application.PlayerController;
import com.spellrush.presentation.Components.HealthBar;

public class GameUIView extends SurfaceView implements SurfaceHolder.Callback
{
    private PlayerController player;
    private HealthBar hpUI;

    private SurfaceHolder surfaceHolder = null;
    private Paint paint = null;

    public GameUIView(Context context){
        super(context);

        setFocusable(true);

        if(surfaceHolder == null){
            surfaceHolder = getHolder();
            surfaceHolder.addCallback(this);
        }

        if(paint == null){
            paint = new Paint();
        }

        this.setBackgroundColor(Color.BLACK);
        this.setZOrderOnTop(true);

        player = new PlayerController();
        hpUI = new HealthBar(20,100,900,50, 15);
    }

    public void update(){
        player.update();
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        if(canvas != null) {
            hpUI.drawHealthBar(canvas, player.MAX_HP, player.getHP());
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Canvas canvas = surfaceHolder.lockCanvas();
        this.draw(canvas);
        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
