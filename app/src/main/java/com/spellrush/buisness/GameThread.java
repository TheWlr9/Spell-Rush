package com.spellrush.buisness;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

import com.spellrush.presentation.Views.GameView;

import static android.content.ContentValues.TAG;



public class GameThread extends Thread {


    public static final int FRAMES_PER_SECOND = 30;
    public static final int SKIP_TICKS = 1000 / FRAMES_PER_SECOND;

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
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        int frameCount = 0;

        while (isRunning){
            startTime = System.nanoTime();
            this.runGameFrame();

            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = SKIP_TICKS - timeMillis;

            sleepUntilNextFrame(waitTime);

            totalTime += System.nanoTime() - startTime;
            frameCount++;

            if (frameCount == FRAMES_PER_SECOND) {
                frameCount = 0;
                totalTime = 0;
            }
        }
    }

    private void sleepUntilNextFrame(long time){
        if(time >= 0){
            try{
                sleep(time);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        else {
            // Running behind schedule, oh no!
        }
    }

    private void runGameFrame(){
        canvas = null;
        try{
            canvas = surfaceHolder.lockCanvas();
            synchronized (surfaceHolder){
                this.updateGame();
                this.displayGame(canvas);
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

    private void updateGame(){
        this.gameView.update();
    }

    private void displayGame(Canvas canvas){
        this.gameView.draw(canvas);
    }

    public void setRunning(boolean isRunning){
        this.isRunning = isRunning;
    }


}
