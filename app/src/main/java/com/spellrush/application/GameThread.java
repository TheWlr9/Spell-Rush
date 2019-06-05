package com.spellrush.application;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

import com.spellrush.presentation.Views.GameView;

import static android.content.ContentValues.TAG;


public class GameThread extends Thread {

    private SurfaceHolder surfaceHolder;
    private GameView gameView;
    private boolean isRunning;

    public static Canvas canvas;

    public GameThread(SurfaceHolder surfaceHolder, GameView gameView){
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;
    }

    @Override
    public void run(){
        while (isRunning){
            canvas = null;
            try{
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    this.gameView.update();
                    this.gameView.draw(canvas);
                }
            } catch(Exception e){
                Log.e(TAG, "run: ",e);
            }
            finally {
                if (canvas != null){
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch(Exception e) {
                        Log.e("ERROR", "run: ",e);
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void setRunning(boolean isRunning){
        this.isRunning = isRunning;
    }


}
