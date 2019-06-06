package com.spellrush.presentation.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.spellrush.buisness.GameThread;

import java.util.ArrayList;

/*******************************************************
* GameView
*
* The View displayed in the GameActivity (Game Screen).
*******************************************************/
public class GameView extends SurfaceView implements SurfaceHolder.Callback
{
    private ArrayList<GameViewLayer> ViewLayers;
    private GameThread thread;

    // GameView Constructor. Create the View Layers and Create the main game thread.
    public GameView(Context context){
        super(context);
        ViewLayers = createGameViewLayers(context);

        getHolder().addCallback(this);
        thread = new GameThread(getHolder(), this);
        setFocusable(true);
    } // end constructor method

    // Create all the layers of the game view.
    private ArrayList<GameViewLayer> createGameViewLayers(Context context){
        ArrayList<GameViewLayer> newLayers = new ArrayList<GameViewLayer>();
        newLayers.add(new GameViewHUDLayer(context));
        return newLayers;
    }

    public void update(){
        // Update each of the GameViewLayer objects.
        for (GameViewLayer layer : ViewLayers) {
            layer.update();
        }
    } // end update()

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        // Draw each of the GameViewLayer objects.
        for (GameViewLayer layer : ViewLayers) {
            layer.draw(canvas);
        }
    } // end draw()

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        // Start the main game loop.
        thread.setRunning(true);
        thread.start();
    } // end surfaceCreated()

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        // Stop the main game thread.
        try {
            thread.setRunning(false);
            thread.join();
        } catch (Exception e) {
            Log.e("ERROR", "run: ",e);
            e.printStackTrace();
        }
    } // end surfaceDestroyed()
}
