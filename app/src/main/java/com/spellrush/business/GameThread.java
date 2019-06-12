package com.spellrush.business;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

import static android.content.ContentValues.TAG;

/*******************************************
 * GameThread
 *
 * The thread that runs the game,
 * Calls the update function for all game objects,
 * Calls the draw function of the GameView
 * *****************************************/
public class GameThread extends Thread {

    public static final int FRAMES_PER_SECOND = 30;
    public static final int SKIP_TICKS = 1000 / FRAMES_PER_SECOND;

    private SurfaceHolder surfaceHolder; // Holds the GameView SurfaceView object
    private com.spellrush.business.GameView gameView;
    private boolean isRunning;

    public static Canvas canvas;

    public GameThread(SurfaceHolder surfaceHolder, GameView gameView){
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;
    }

    // run - Manage the game loop. Ensure the loop iterates once per frame (According to FPS).
    @Override
    public void run(){
        long timeAtFrameStart;
        long timeAtFrameEndInMs;
        long waitTime;

        // MAIN GAME LOOP - Iterating once per frame
        while (isRunning){
            // Get current program time before running frame
            timeAtFrameStart = System.nanoTime();

            // Do all game updates once per frame.
            this.runGameFrame();

            // Calculate time until next frame and sleep until then
            timeAtFrameEndInMs = (System.nanoTime() - timeAtFrameStart) / 1000000;
            waitTime = SKIP_TICKS - timeAtFrameEndInMs;
            sleepUntilNextFrame(waitTime);
        }
    } // end run()

    // sleepUntilNextFrame - Put the thread to sleep until it's time for the next frame.
    private void sleepUntilNextFrame(long time){
        if(time >= 0){
            try{
                sleep(time);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else {
            // Running behind schedule, oh no!
        }
    } // end sleepUntilNextFrame()

    // runGameFrame - Called once per frame. Update all game objects, and draw the canvas.
    private void runGameFrame(){
        canvas = null;
        try{
            // Lock the canvas and surface holder to this Thread to ensure concurrency. (See COMP 3430)
            canvas = surfaceHolder.lockCanvas();
            synchronized (surfaceHolder) {
                this.updateGame();
                this.displayGame(canvas);
            }
        } catch(Exception e) {
            Log.e(TAG, "run: ",e);
        }
        finally {
            if (canvas != null){
                try {
                    // Unlock (free) the canvas after this thread is done with them.
                    surfaceHolder.unlockCanvasAndPost(canvas);
                } catch(Exception e) {
                    Log.e("ERROR", "run: ",e);
                    e.printStackTrace();
                }
            }
        }
    } // end runGameFrame()

    // updateGame - Call on all game objects to update.
    private void updateGame(){
        this.gameView.update();
    }

    // displayGame - Call on all view objects to draw.
    private void displayGame(Canvas canvas){
        this.gameView.draw(canvas);
    }

    // setRunning(true) will enable the main loop in the run() method.
    public void setRunning(boolean isRunning){
        this.isRunning = isRunning;
    }

} // end GameThread class
