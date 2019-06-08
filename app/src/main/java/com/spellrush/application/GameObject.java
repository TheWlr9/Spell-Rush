package com.spellrush.application;

import android.graphics.Canvas;

/**
 * GameView Layer
 *
 * Abstract class for layers on top of the GameView
 */
public abstract class GameObject implements Comparable<GameObject>{
    // Specifies the order in which elements are drawn to the screen
    public int drawDepth;

    public GameObject(int drawDepth){
        this.drawDepth = drawDepth;
    }

    public abstract void update();
    public abstract void draw(Canvas canvas);

    @Override
    public int compareTo(GameObject o) {
        Integer myDepth = new Integer(this.drawDepth);
        Integer otherDepth = new Integer(o.drawDepth);
        return myDepth.compareTo(otherDepth);
    }
}
