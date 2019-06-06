package com.spellrush.presentation.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.spellrush.buisness.GameThread;

public class GameView extends SurfaceView implements SurfaceHolder.Callback
{
    private GameHUDView gameHUDView;
    private GameThread thread;

    public GameView(Context context){
        super(context);
        gameHUDView = new GameHUDView(context);
        getHolder().addCallback(this);
        thread = new GameThread(getHolder(), this);
        setFocusable(true);
    } // end constructor method

    public void update(){
        gameHUDView.update();
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        gameHUDView.draw(canvas);
    } // end draw()

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        thread.setRunning(true);
        thread.start();
    } // end surfaceCreated()

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
    } // end surfaceDestroyed()
}
