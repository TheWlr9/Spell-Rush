package com.spellrush.objects.attacks;

import android.graphics.Canvas;

import com.spellrush.audio.AudioManager;
import com.spellrush.audio.AudioManagerError;
import com.spellrush.audio.SoundEvent;
import com.spellrush.business.PlayerController;
import com.spellrush.objects.Enemy;
import com.spellrush.objects.GameObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * GameBoard
 *
 * For now built with the assumption of a single lane with attacks moving in a single direction.
 *
 * This will serve the purposes of AttackObject coordination.
 * This is not static because the caller should be able to encapsulate a GameBoard object.
 * This will form the complete interface for AttackObjects.
 *
 *
 */

public class GameBoard extends GameObject{

    static final int BULLET_DEPTH = -100;
    static final int HIT_POINTS = 100;

    //game board "rules"
    private ArrayList<AttackObject> attacks; //a list of all attacks on the board

    // Temporary lists to avoid concurrent AttackObject array access
    private Queue<AttackObject> attacksToAdd;
    private Queue<AttackObject> attacksToDelete;

    private final int numLanes;
    private final int laneTopPosition; // Y Position of the top of the lane (Enemy goal zone)
    private final int laneLength; //number of discrete units between lane start and end
    private final int maxObjects; //maximum number of objects the board will allow per side

    private boolean clearing;
    private int playerAttackCount = 0; //number of player attacks on board (may not exceed maxObjects/2)

    /**
     * Constructor
     *
     * maxObjects is here to guarantee that we don't need to allocate new ArrayLists
     * when lists of attack info are requested (since frequent list allocation is expensive)
     *
     * @param numLanes the number of lanes the board will account for
     * @param yPos the device y position of where to draw the top of the lane
     * @param laneLength the number of discrete units between the lane start and end
     * @param maxObjects the maximum number of objects that the board will allow
     */
    public GameBoard(int numLanes, int yPos, int laneLength, int maxObjects){
        super(BULLET_DEPTH); //all attacks will be drawn at a depth of 20
        attacks = new ArrayList<AttackObject>(maxObjects);
        attacksToDelete = new LinkedList<>();
        attacksToAdd = new LinkedList<>();
        this.laneTopPosition = yPos;
        this.numLanes = numLanes;
        this.laneLength = laneLength;
        this.maxObjects = maxObjects;
        this.clearing = false;
    }

    // return read-only attack list for testing and otherwise
    public ArrayList<AttackObject> getAttacks() {
        return attacks;
    }
    // return read-only attack list for testing and otherwise
    public Queue<AttackObject> getAttacksToAdd() {
        return attacksToAdd;
    }
    // return read-only attack list for testing and otherwise
    public Queue<AttackObject> getAttacksToDelete() {
        return attacksToDelete;
    }

    /**
     * Clears the board of all attacks.
     */
    public void clear(){
        this.clearing = true;
    }

    /**
     * Adds an attack to the board at the start location of the specified lane.
     *
     * @param newAttack The pre-constructed attack to add to the board
     */
    void addAttack(AttackObject newAttack) {
        if (canAddAttack(newAttack)) {
            attacksToAdd.add(newAttack);
            if(newAttack.isPlayerAttack()){
                playerAttackCount++;
            }
        }
    }

    public int getLaneTopPosition() {
        return laneTopPosition;
    }
    public int getLaneBottomPosition() {
        return laneTopPosition + laneLength;
    }

    /**
     * Update attacks according to the work required for one frame.
     */
    public void update(){
        if(clearing) {
            attacks.clear();
            attacksToAdd.clear();
            attacksToDelete.clear();
            playerAttackCount = 0;
            clearing = false;
        } else {
            for (AttackObject attack : attacks) {
                attack.update();
                checkAttackCollisionsFor(attack);
                checkAttackReachedEnd(attack);
                if (attack.wasDestroyed()) {
                    onAttackDestroyed(attack);
                }
            }
            deleteAttacks();
            addAttacks();
        }
    }

    private void checkAttackCollisionsFor(AttackObject attack){
        for (AttackObject otherAttack : attacks) {
            if(AttackCollisionUtility.areColliding(attack, otherAttack, AttackCollisionUtility.COLLISION_DIST)){
                AttackCollisionUtility.handleCollision(attack, otherAttack, PlayerController.getInstance());
            }
        }
    }

    private void checkAttackReachedEnd(AttackObject attack) {
        if(attack.reachedEnd(this)){
            if(attack.isPlayerAttack()){
                Enemy enemy = Enemy.getInstance();
                if(enemy != null){
                    enemy.getHit(attack.getDamage());

                    PlayerController.getInstance().addScore(HIT_POINTS);
                    
                    try{
                        AudioManager.play(SoundEvent.ENEMY_DAMAGED, true);
                    }
                    catch(AudioManagerError ame){
                        System.err.println("Error playing ENEMY_DAMAGED; non-linked file");
                    }
                }
            }
            else{
                PlayerController.getInstance().getHit(attack.getDamage());

                try{
                    AudioManager.play(SoundEvent.PLAYER_DAMAGED, true);
                }
                catch(AudioManagerError ame){
                    System.err.println("Error playing PLAYER_DAMAGED; non-linked file");
                }
            }
            attack.setDestroyed(true);
        }
    }

    // Delete all objects added to Object Deleting Queue in each Update Frame
    private void deleteAttacks(){
        while(!attacksToDelete.isEmpty()){
            attacks.remove(attacksToDelete.remove());
        }
    }
    // Add all objects added to Object Adding Queue in each Update Frame
    private void addAttacks(){
        while(!attacksToAdd.isEmpty()){
            attacks.add(attacksToAdd.remove());
        }
    }

    /**
     * @param attack The attack you want to try to add
     * @return Whether attack can be added (both player and enemy attack counts
     */
    private boolean canAddAttack(AttackObject attack){
        //if: adding player attack, ensure player attack count not too high
        //then ensure total attack count and lane index are appropriate
        return (!attack.isPlayerAttack() || (playerAttackCount < maxObjects)) &&
                (getAttackCount() < (maxObjects*2) - playerAttackCount) //2 sides of maxObjects each
                && attack.getLane() >= 0 && attack.getLane() <= numLanes;
    }

    /**
     * @return count of attacks currently accounted for on the board.
     */
    private int getAttackCount(){
        return attacks.size() + attacksToAdd.size() - attacksToDelete.size();
    }

    /**
     * When the attack given is destroyed, subtract health from the right entity,
     * and remove it from the attack list.
     *
     * @param attack the attack to operate on
     */
     void onAttackDestroyed(AttackObject attack) {
         attacksToDelete.add(attack);
         if(attack.isPlayerAttack()){
             playerAttackCount--;
         }
    }

    /**
     * draw every attack that we currently have.
     *
     * @param canvas canvas to draw on
     */
    public void draw(Canvas canvas){
        for(AttackObject attack : attacks){
            attack.draw(canvas);
        }
    }

}
