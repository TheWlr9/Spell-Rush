package com.spellrush.objects.attacks;

import android.graphics.Canvas;

/**
 * Back end to track information about individual attacks.
 *
 */

public abstract class AttackObject {

    protected final int ATTACK_WIDTH = 128;
    protected final int ATTACK_HEIGHT = 128;

    //public so accessible, final so cannot be changed.
    final boolean isPlayerAttack; //the "allegiance" of this attack.
    final int lane; //the lane this attack belongs to
    private final int laneEnd;

    protected int yPos; //this attack's position within its lane.
    protected int xPos; // X position on the screen
    private int speed;
    private int damage;
    private boolean destroyed = false; //kept here in case caller wants to animate object destruction

    /**
     * Constructor
     *
     * @param isPlayerAttack whether this is a player attack
     * @param lane which lane this belongs to
     * @param speed what speed this attack will travel at
     * @param laneStart Y value of the bullet's spawning position
     * @param laneEnd Y value of the "goal zone"
     * @param damage How much is subtracted from the enemy/player HP on goal
     */
    protected AttackObject(boolean isPlayerAttack, int lane, int speed, int laneStart, int laneEnd, int damage) {
        this.isPlayerAttack = isPlayerAttack;
        if(isPlayerAttack){
            speed = -speed; // Set direction of bullet to upwards
        }
        this.lane = lane;
        this.speed = speed;
        this.yPos = laneStart;
        this.laneEnd = laneEnd;
        this.damage = damage;
        this.xPos = calculateCanvasXPosition(lane);
    }

    /**
     * @return how many positions the attack has traversed in the lane.
     */
    int getYPosition(){ return yPos;}

    /**
     * @return the current speed of this attack
     */
    int getSpeed(){
        return speed;
    }

    int getDamage(){
        return damage;
    }

    /**
     * @return whether this attack has collided with another attack
     */
    boolean wasDestroyed(){
        return destroyed;
    }

    /**
     * Update attack position and location, destroy if we have gone past the end of the lane.
     */
    private void updatePosition(){
        yPos += speed;
        destroyed = (isPlayerAttack && yPos < laneEnd) || (!isPlayerAttack && yPos > laneEnd);
    }


    /**
     * This will tell us where to draw in the X-axis based on the width of the canvas and the lane
     * of the attack.
     *
     * @param lane
     * @return
     */
    private int calculateCanvasXPosition(int lane){
        // TODO: dummy value for now, update this if we're doing lanes.
        if(isPlayerAttack)
            return 600;
        else
            return 620;
    }

    public void update() {
        updatePosition();
    }

    public abstract void draw(Canvas canvas);

}
