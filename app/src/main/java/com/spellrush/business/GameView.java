package com.spellrush.business;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.spellrush.application.Enemy;
import com.spellrush.objects.ExampleBall;
import com.spellrush.objects.GameObject;
import com.spellrush.presentation.UI.FingerPathLayer;
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

    private FingerPathLayer fingerPathLayer;
    private ShapeRecognition drawingAI;
    private PlayerController player;

    /** Default Constructors Required by SurfaceView **/

    public GameView(Context context){
        super(context);
        init();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public GameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    // GameView Constructor. Create the initial Game Objects and the main game thread.
    private void init(){
        fingerPathLayer = new FingerPathLayer();
        drawingAI = new ShapeRecognition(fingerPathLayer);

        // Setup the View
        this.setupView();

        // Create the game thread
        thread = new GameThread(getHolder(), this);

        // Initialize the GameObjects list
        GameObjects = createStartupObjects();
    } // end init()

    private void setupView(){
        getHolder().addCallback(this); // TODO: explain what this does
        getHolder().setFormat(PixelFormat.TRANSPARENT);
        setZOrderMediaOverlay(true);
        setFocusable(true);
    }

    // Create the game objects present at the game start
    private ArrayList<GameObject> createStartupObjects(){
        ArrayList<GameObject> newObjects = new ArrayList<GameObject>();

        player = new PlayerController();
        fingerPathLayer = new FingerPathLayer();
        drawingAI = new ShapeRecognition(fingerPathLayer);

        newObjects.add(player);
        newObjects.add(new GameHUD(player));
        newObjects.add(fingerPathLayer);
        newObjects.add(new Enemy(400,400,50,30));

        Collections.sort(newObjects, Collections.reverseOrder()); // Set order based on depth

        return newObjects;
    } // end createStartupObjects()

    // createObject
    // Add new game object to list of game objects
    public void addObject(GameObject newObject){
        GameObjects.add(newObject.drawDepth, newObject);
    }

    // destroyObject
    // Remove game object from list of game objects
    public void removeObject(GameObject oldObject){
        GameObjects.remove(oldObject);
    }

    public void update(){
        // Update each of the GameViewLayer objects.
        for (GameObject object : GameObjects) {
            object.update();
        }
        drawingAI.hasValidDrawnEvent();
    } // end update()

    @Override
    public void draw(Canvas canvas){
        if(canvas != null) {
            super.draw(canvas);
            // Draw each of the GameViewLayer objects.
            for (GameObject object : GameObjects) {
                object.draw(canvas);
            }
        }
    } // end draw()

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        // Start the main game loop.
        thread.setRunning(true);
        thread.start();
    } // end surfaceCreated()

    public boolean onTouchEvent(MotionEvent event){
        boolean value = false;

        value = fingerPathLayer.onTouchEvent(event);

        return value;
    } // end of onTouchEvent(event)


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
