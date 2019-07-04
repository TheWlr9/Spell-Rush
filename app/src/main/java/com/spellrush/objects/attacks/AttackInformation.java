package com.spellrush.objects.attacks;


/**
 * An object that contains information about an attack.
 * Its purpose is to abstract away attack information.
 */
public class AttackInformation {

    boolean isPlayerAttack;
    int lane;
    int speed;
    int laneStart;
    int laneEnd;
    int damage;


    /**
     *
     * @param isPlayerAttack whether this is a player attack
     * @param lane which lane this belongs to
     * @param speed what speed this attack will travel at
     * @param laneStart Y value of the bullet's spawning position
     * @param laneEnd Y value of the "goal zone"
     * @param damage How much is subtracted from the enemy/player HP on goal
     */
    AttackInformation(boolean isPlayerAttack, int lane, int speed, int laneStart, int laneEnd, int damage){
        this.isPlayerAttack = isPlayerAttack;
        this.lane = lane;
        this.speed = isPlayerAttack? -speed : speed;
        this.laneStart = laneStart;
        this.laneEnd = laneEnd;
        this.damage = damage;
    }
}
