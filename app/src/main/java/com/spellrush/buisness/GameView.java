package com.spellrush.buisness;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.spellrush.application.Enemy;
import com.spellrush.application.ExampleBall;
import com.spellrush.application.GameObject;
import com.spellrush.buisness.GameThread;
import com.spellrush.presentation.UI.GameHUD;

import java.util.ArrayList;
import java.util.Collections;

/*******************************************************
* GameView
*
* The View displayed in the GameActivity (Game Screen).
 *
 * Maintains all game objects within the view, and calls
 * their update and draw methods once per frame.
*******************************************************/
public class GameView extends SurfaceView implements SurfaceHolder.Callback
{
    private ArrayList<GameObject> GameObjects;
    private GameThread thread;

    // GameView Constructor. Create the initial Game Objects and the main game thread.
    public GameView(Context context){
        super(context);
        // Setup the View
        getHolder().addCallback(this);
        getHolder().setFormat(PixelFormat.TRANSPARENT);
        setZOrderMediaOverlay(true);
        setFocusable(true);

        // Create the game thread
        thread = new GameThread(getHolder(), this);

        // Initialize the GameObjects list
        GameObjects = createStartupObjects();
    } // end constructor method

    // Create the game objects present at the game start
    private ArrayList<GameObject> createStartupObjects(){
        ArrayList<GameObject> newObjects = new ArrayList<GameObject>();

        newObjects.add(new GameHUD());
        newObjects.add(new ExampleBall());
        newObjects.add(new Enemy(400,400,50,60));

        Collections.sort(newObjects, Collections.reverseOrder()); // Set order based on depth
        return newObjects;
    } // end createStartupObjects()

    // createObject
    // Add new game object to list of game objects
    public void createObject(GameObject newObject){
        GameObjects.add(newObject.drawDepth, newObject);
    }

    // destroyObject
    // Remove game object from list of game objects
    public void destroyObject(GameObject oldObject){
        GameObjects.remove(oldObject);
    }

    public void update(){
        // Update each of the GameViewLayer objects.
        for (GameObject object : GameObjects) {
            object.update();
        }
    } // end update()

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        // Draw each of the GameViewLayer objects.
        for (GameObject object : GameObjects) {
            object.draw(canvas);
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
