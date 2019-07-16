package com.spellrush.objects.attacks;

import android.content.res.Resources;
import android.graphics.Canvas;

import com.spellrush.presentation.ISpriteDrawer;
import com.spellrush.presentation.SpriteDrawer;

/**
 * Back end to track information about individual attacks.
 *
 */

public abstract class AttackObject {

    protected final int ATTACK_WIDTH = 128;
    protected final int ATTACK_HEIGHT = 128;

    protected AttackInformation attackInfo;

    //public so accessible, final so cannot be changed.
    protected int yPos; //this attack's position within its lane.
    protected int xPos; // X position on the screen
    private boolean destroyed = false;

    /**
     * Kept so current tests aren't broken.
     */
    AttackObject(boolean isPlayerAttack, int lane, int speed, int damage, int y){
        this.attackInfo = new AttackInformation(isPlayerAttack, lane, speed, damage);
        this.yPos = y;
    }

    /**
     * Constructor
     *
     * @param attackInfo information about the attack.
     * @param y Starting Y Position of the object on the screen
     */
    protected AttackObject(AttackInformation attackInfo, int y) {
        this.attackInfo = attackInfo;
        this.yPos = y;
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

    public void update() {
        if(!this.wasDestroyed()) {
            updatePosition();
        }
    }

    // The draw method called by the GameBoard / Attack object manager
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
        ISpriteDrawer drawer = SpriteDrawer.getInstance();
        int xPos = (Resources.getSystem().getDisplayMetrics().widthPixels  / 2) - (ATTACK_WIDTH / 2);
        int spriteIndex = (attackInfo.isPlayerAttack) ? playerSprite : enemySprite;
        drawer.drawSprite(canvas, spriteIndex, xPos, yPos, ATTACK_WIDTH, ATTACK_HEIGHT);
    }
}
