package com.spellrush.presentation;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.spellrush.application.PlayerController;
import com.spellrush.presentation.Components.HealthBar;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private PlayerController player;
    private HealthBar hpUI;


    public GameView(Context context){
        super(context);
        getHolder().addCallback(this);
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        player = new PlayerController();
        hpUI = new HealthBar(20,20,500,30);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){

    }

    public void update(){
        player.update();
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        if(canvas != null){
            hpUI.drawHealthBar(canvas, player.MAX_HP, player.getHP());
        }
    }
}
