package com.spellrush.presentation.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.spellrush.application.GameThread;


public class GameView extends SurfaceView implements SurfaceHolder.Callback
{
    private GameUIView gameUiView;

    private GameThread thread;

    public GameView(Context context){
        super(context);

        gameUiView = new GameUIView(context);

        getHolder().addCallback(this);

        thread = new GameThread(getHolder(), this);
        setFocusable(true);

    }

    public void update(){
        gameUiView.update();
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        gameUiView.draw(canvas);
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
