package com.spellrush.application;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

import com.spellrush.presentation.GameUIView;

import static android.content.ContentValues.TAG;


public class GameThread extends Thread {

    private SurfaceHolder surfaceHolder;
    private GameUIView gameUiView;
    private boolean isRunning;

    public static Canvas canvas;

    public GameThread(SurfaceHolder surfaceHolder, GameUIView gameUiView){
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameUiView = gameUiView;
    }

    @Override
    public void run(){
        while (isRunning){
            canvas = null;
            try{
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    this.gameUiView.update();
                    this.gameUiView.draw(canvas);
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
