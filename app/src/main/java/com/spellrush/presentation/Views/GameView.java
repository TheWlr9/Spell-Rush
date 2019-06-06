package com.spellrush.presentation.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.spellrush.buisness.GameThread;

/*******************************************************
* GameView
*
* The View displayed on the GameActivity (Game) Screen.
*******************************************************/
public class GameView extends SurfaceView implements SurfaceHolder.Callback
{
    private GameViewHUDLayer gameViewHUDLayer;
    private GameThread thread;

    public GameView(Context context){
        super(context);
        gameViewHUDLayer = new GameViewHUDLayer(context);
        getHolder().addCallback(this);
        thread = new GameThread(getHolder(), this);
        setFocusable(true);
    } // end constructor method

    public void update(){
        gameViewHUDLayer.update();
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        gameViewHUDLayer.draw(canvas);
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
