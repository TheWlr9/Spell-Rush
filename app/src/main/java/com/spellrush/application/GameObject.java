package com.spellrush.application;

import android.graphics.Canvas;

/**
 * GameObject
 *
 * Abstract class for any game object that needs to be updated and drawn to the screen
 */
public abstract class GameObject implements Comparable<GameObject>{
    // Specifies the order in which elements are drawn to the screen
    public int drawDepth;

    public GameObject(int drawDepth){
        this.drawDepth = drawDepth;
    }

    // Called once per frame by the GameView.
    public abstract void update();

    // Draw to the canvas.
    public abstract void draw(Canvas canvas);

    @Override
    public int compareTo(GameObject o) {
        Integer myDepth = new Integer(this.drawDepth);
        Integer otherDepth = new Integer(o.drawDepth);
        return myDepth.compareTo(otherDepth);
    }
}
