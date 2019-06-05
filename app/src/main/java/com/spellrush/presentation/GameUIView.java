package com.spellrush.presentation;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.spellrush.application.GameThread;
import com.spellrush.application.PlayerController;
import com.spellrush.presentation.Components.HealthBar;

public class GameUIView extends SurfaceView implements SurfaceHolder.Callback
{
    private PlayerController player;
    private HealthBar hpUI;

    private GameThread thread;

    public GameUIView(Context context){
        super(context);


        player = new PlayerController();
        hpUI = new HealthBar(20,100,900,50, 15);

        getHolder().addCallback(this);

        thread = new GameThread(getHolder(), this);
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
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        try {
            thread.setRunning(false);
            thread.join();
        } catch (Exception e) {
            Log.e("ERROR", "run: ",e);
            e.printStackTrace();
        }
    }
}
