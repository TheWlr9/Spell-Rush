package com.spellrush.objects.attacks;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.spellrush.business.GameView;

/**
 * Back end to track information about individual attacks.
 *
 */

public abstract class AttackObject {

    /* Used to tell the addAttack what type of attack it should create*/
    public enum AttackType{
        Fire, Water, Ground
    }

    protected final int ATTACK_WIDTH = 128;
    protected final int ATTACK_HEIGHT = 128;

    protected AttackInformation attackInfo;
    protected AttackType type;

    //public so accessible, final so cannot be changed.
    protected int yPos; //this attack's position within its lane.
    protected int xPos; // X position on the screen
    private boolean destroyed = false;

    /**
     * Kept so current tests aren't broken.
     */
    AttackObject(boolean isPlayerAttack, int lane, int speed, int damage, AttackType type, int y){
        this.attackInfo = new AttackInformation(isPlayerAttack, lane, speed, damage);
        this.type = type;
        this.yPos = y;
        this.xPos = calculateCanvasXPosition(attackInfo.lane);
    }

    /**
     * Constructor
     *
     * @param attackInfo information about the attack.
     * @param type What type of attack is it?
     */
    protected AttackObject(AttackInformation attackInfo, AttackType type, int y) {
        this.attackInfo = attackInfo;
        this.type = type;
        this.yPos = y;
        this.xPos = calculateCanvasXPosition(attackInfo.lane);
    }

    boolean reachedEnd(GameBoard board){
        return isPlayerAttack() ? yPos < board.getLaneTopPosition() : yPos > board.getLaneBottomPosition();
    }

    boolean hasSameAllegiance(AttackObject attackToCheck){
        return isPlayerAttack() == attackToCheck.isPlayerAttack();
    }

    boolean isPlayerAttack(){
        return attackInfo.isPlayerAttack;
    }

    /**
     * @return how many positions the attack has traversed in the lane.
     */
    int getYPosition(){ return yPos;}

    /**
     * @return the current speed of this attack
     */
    int getSpeed(){
        return attackInfo.speed;
    }

    int getDamage(){
        return attackInfo.damage;
    }

    AttackType getType() { return type; }

    int getLane() { return attackInfo.lane; }

    /**
     * @return whether this attack has collided with another attack
     */
    boolean wasDestroyed(){
        return destroyed;
    }

    void setDestroyed(boolean setter){
        destroyed = setter;
    }

    /**
     * Update attack position and location, destroy if we have gone past the end of the lane.
     */
    private void updatePosition(){
        yPos += attackInfo.speed;
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
        if(attackInfo.isPlayerAttack)
            return 600;
        else
            return 620;
    }

    public void update() {
        if(!this.wasDestroyed()) {
            updatePosition();
        }
    }

    public abstract void draw(Canvas canvas);


    /**
     * Draws an attack object, after being told what to draw.
     * Can be used by subclasses if they want to draw "by default"
     *
     * @param canvas canvas to draw on
     * @param playerSprite sprite to draw if player
     * @param enemySprite sprite to draw if enemy
     */
    protected void draw(Canvas canvas, int playerSprite, int enemySprite) {
        Resources r = GameView.getInstance().getContext().getResources();
        Drawable sprite = (attackInfo.isPlayerAttack) ? r.getDrawable(playerSprite) : r.getDrawable(enemySprite);
        sprite.setBounds(xPos, yPos, xPos + ATTACK_WIDTH, yPos + ATTACK_HEIGHT);
        sprite.draw(canvas);
    }
}
