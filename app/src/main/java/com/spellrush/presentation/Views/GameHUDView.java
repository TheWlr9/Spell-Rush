package com.spellrush.presentation.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.spellrush.application.PlayerController;
import com.spellrush.presentation.Views.Components.HealthBar;

public class GameHUDView extends SurfaceView implements SurfaceHolder.Callback
{
    private PlayerController player;
    private HealthBar hpUI;


    public GameHUDView(Context context){
        super(context);


        player = new PlayerController();
        hpUI = new HealthBar(20,100,900,50, 15);

        getHolder().addCallback(this);
        setFocusable(true);

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

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
