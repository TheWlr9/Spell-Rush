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
    public AttackObject(boolean isPlayerAttack, int lane, int speed,
                           int laneStart, int laneEnd, int damage, AttackType type){
        this.attackInfo = new AttackInformation(isPlayerAttack, lane, speed, laneStart, laneEnd, damage);
        this.type = type;
        this.yPos = isPlayerAttack ? attackInfo.laneEnd : attackInfo.laneStart;
    }

    /**
     * Constructor
     *
     * @param attackInfo information about the attack.
     * @param type What type of attack is it?
     */
    protected AttackObject(AttackInformation attackInfo, AttackType type) {
        this.attackInfo = attackInfo;
        this.type = type;
        this.yPos = attackInfo.laneStart;
    }

    boolean reachedEnd(){
        return isPlayerAttack() ? getYPosition() < getLaneEnd() : getYPosition() > getLaneEnd();
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

    int getLaneEnd(){
        return attackInfo.laneEnd;
    }

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
        int xPos = (Resources.getSystem().getDisplayMetrics().widthPixels  / 2) - (ATTACK_WIDTH / 2);
        Drawable sprite = (attackInfo.isPlayerAttack) ? r.getDrawable(playerSprite) : r.getDrawable(enemySprite);
        sprite.setBounds(xPos, yPos, xPos + ATTACK_WIDTH, yPos + ATTACK_HEIGHT);
        sprite.draw(canvas);
    }
}
