package com.spellrush.objects;

import android.graphics.Canvas;

public abstract class HealthObject extends GameObject {
    protected final int MAX_HP;
    protected int hp;

    public HealthObject(int drawDepth, int maxhp) {
        super(drawDepth);
        MAX_HP = maxhp;
        hp = MAX_HP;
    }

    // Called when HP goes below 0
    protected abstract void onDestroyed();

    public int getHP(){
        return hp;
    }
    public int getMaxHP(){
        return MAX_HP;
    }

    // Reduce player HP by amount
    public int getHit(int amount) {
        if(amount > 0) { // edge case: amount is negative
            hp = Math.max(hp - amount, 0);
            if(hp <= 0){
                onDestroyed();
            }
        }
        return hp;
    }

    // Increase player HP by amount
    public int addHP( int amount) {
        if(amount > 0) { // edge case: amount is negative
            hp = Math.min(hp + amount, MAX_HP);
        }
        return hp;
    }

    // Called once per frame by the GameView.
    @Override
    public abstract void update();
    // Draw to the canvas.
    @Override
    public abstract void draw(Canvas canvas);

}
