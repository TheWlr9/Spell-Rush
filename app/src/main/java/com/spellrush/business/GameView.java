package com.spellrush.business;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.spellrush.objects.GameObject;
import com.spellrush.presentation.UI.FingerPathLayer;
import com.spellrush.presentation.UI.GameHUD;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

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

    private static final int MAX_BULLETS = 100;

    // Follow Singleton design pattern
    private static GameView instance;

    private static ArrayList<GameObject> gameObjects;
    private static PlayerController player;
    private static LevelManager levelManager;
    private GameThread thread;
    private FingerPathLayer fingerPathLayer;
    private ShapeRecognition drawingAI;

    // Temporary lists to avoid concurrent GameObject array access
    private static Queue<GameObject> objectsToDelete;
    private static Queue<GameObject> objectsToAdd;

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
        // Setup the View
        this.setupView();

        // Create the game thread
        thread = new GameThread(getHolder(), this);

        // Initialize the gameObjects list
        gameObjects = createStartupObjects();
        objectsToDelete = new LinkedList<>();
        objectsToAdd = new LinkedList<>();

        instance = this;
    } // end init()

    public static GameView getInstance(){
        return instance;
    }

    private void setupView(){
        getHolder().addCallback(this); // This allows the view to process changes and events
        getHolder().setFormat(PixelFormat.TRANSPARENT);
        setZOrderMediaOverlay(true);
        setFocusable(true);
    }

    // Create the game objects present at the game start
    private ArrayList<GameObject> createStartupObjects(){
        ArrayList<GameObject> newObjects = new ArrayList<GameObject>();

        levelManager = LevelManager.getInstance();
        player = PlayerController.getInstance();
        fingerPathLayer = new FingerPathLayer();
        drawingAI = new ShapeRecognition(fingerPathLayer);

        newObjects.add(player);
        newObjects.add(fingerPathLayer);
        newObjects.add(levelManager);
        newObjects.add(new GameHUD());

        Collections.sort(newObjects, Collections.reverseOrder()); // Set order based on depth

        return newObjects;
    } // end createStartupObjects()

    // createObject
    // Add new game object to list of game objects
    public static void addObject(GameObject newObject){
        if(gameObjects != null && newObject != null) {
            // Add the object to a temporary list while the gameObjects list is still updating
            objectsToAdd.add(newObject);
        }
    }

    // destroyObject
    // Remove game object from list of game objects
    public static void removeObject(GameObject oldObject){
        if(gameObjects != null && oldObject != null) {
            // Add the object to a temporary list while the gameObjects list is still updating
            objectsToDelete.add(oldObject);
        }
    }

    public void update(){
        // Update each of the GameViewLayer objects.
        for (GameObject object : gameObjects) {
            object.update();
        }
        drawingAI.hasValidDrawnEvent();
        deleteObjects();
        addObjects();

    } // end update()

    // Delete all objects added to Object Deleting Queue in this Update Frame
    private void deleteObjects(){
        while(!objectsToDelete.isEmpty()){
            gameObjects.remove(objectsToDelete.remove());
        }
    }
    // Add all objects added to Object Adding Queue in this Update Frame
    private void addObjects(){
        while(!objectsToAdd.isEmpty()){
            GameObject newObj = objectsToAdd.remove();
            gameObjects.add(newObj.drawDepth, newObj);
        }
    }

    @Override
    public void draw(Canvas canvas){
        if(canvas != null) {
            super.draw(canvas);
            // Draw each of the GameViewLayer objects.
            for (GameObject object : gameObjects) {
                object.draw(canvas);
            }
        }
    } // end draw()

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (thread.getState() == Thread.State.TERMINATED){
            // Create a new game thread to run the game on
            thread = new GameThread(getHolder(), this);
        }
        if (thread.getState() == Thread.State.NEW) {
            // Start the main game loop.
            thread.setRunning(true);
            thread.start();
        }
    } // end surfaceCreated()

    @Override
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

        player.reset();
        levelManager.reset();
    } // end surfaceDestroyed()

}
