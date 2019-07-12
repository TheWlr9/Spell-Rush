package com.spellrush.objects.attacks;

import android.graphics.Canvas;

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

    static final int BULLET_DEPTH = 20;

    //for simplified reference to an attack type (without all the in-method dot vomit)
    private static final AttackObject.AttackType FIRE = AttackObject.AttackType.Fire;
    private static final AttackObject.AttackType WATER = AttackObject.AttackType.Water;
    private static final AttackObject.AttackType GROUND = AttackObject.AttackType.Ground;

    //game board "rules"
    private ArrayList<AttackObject> attacks; //a list of all attacks on the board

    // Temporary lists to avoid concurrent AttackObject array access
    private Queue<AttackObject> attacksToAdd;
    private Queue<AttackObject> attacksToDelete;

    private final int numLanes;
    private final int laneTopPosition; // Y Position of the top of the lane (Enemy goal zone)
    private final int laneLength; //number of discrete units between lane start and end
    private final int maxObjects; //maximum number of objects the board will allow.

    private boolean clearing;

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
    ArrayList<AttackObject> getAttacks() {
        return attacks;
    }
    // return read-only attack list for testing and otherwise
    Queue<AttackObject> getAttacksToAdd() {
        return attacksToAdd;
    }
    // return read-only attack list for testing and otherwise
    Queue<AttackObject> getAttacksToDelete() {
        return attacksToDelete;
    }

    /**
     * Clears the board of all attacks.
     */
    public void clear(){ this.clearing = true; }

    /**
     * Adds an attack to the board at the start location of the specified lane.
     *
     * @param type The attack type to create
     * @param isPlayerAttack The attack's allegiance (and direction of movement).
     * @param laneIndex The lane the attack will start in.
     * @param speed what speed this attack will travel at
     * @param damage How much is subtracted from the enemy/player HP on goal
     */
    void addAttack(AttackObject.AttackType type, boolean isPlayerAttack, int laneIndex, int speed, int damage){

        //if bad lane index or no space, quit.
        if(laneIndex < 0 || laneIndex >= numLanes || !canAddAttack()){
            return;
        }
        // Set laneStart / laneEnd according to direction
        int laneStart = isPlayerAttack ? laneLength : laneTopPosition;
        int laneEnd = isPlayerAttack ? laneTopPosition : laneLength;

        // create attack object
        AttackObject newAttack;
        AttackInformation attackInfo = new AttackInformation(
                isPlayerAttack, laneIndex, speed, laneStart, laneEnd, damage);
        switch(type){
            case Fire:
                newAttack = new FireAttack(attackInfo);
                break;
            case Water:
                newAttack = new WaterAttack(attackInfo);
                break;
            case Ground:
                newAttack = new GroundAttack(attackInfo);
                break;
            default:
                newAttack = new FireAttack(attackInfo);
                break;
        }
        attacksToAdd.add(newAttack);
    }

    /**
     * Update attacks according to the work required for one frame.
     */
    public void update(){
        if(clearing) {
            attacks.clear();
            attacksToAdd.clear();
            attacksToDelete.clear();
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
        if(attack.reachedEnd()){
            if(attack.isPlayerAttack()){
                Enemy enemy = Enemy.getInstance();
                if(enemy != null){
                    enemy.getHit(attack.getDamage());
                }
            }
            else{
                PlayerController.getInstance().getHit(attack.getDamage());
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
     * @return whether we have enough space to add a new attack
     */
    private boolean canAddAttack(){
        return (attacks.size() + attacksToAdd.size() - attacksToDelete.size()) < maxObjects;
    }

    /**
     * When the attack given is destroyed, subtract health from the right entity,
     * and remove it from the attack list.
     *
     * @param attack the attack to operate on
     */
     void onAttackDestroyed(AttackObject attack) {
        attacksToDelete.add(attack);
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