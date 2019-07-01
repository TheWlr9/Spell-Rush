package com.spellrush.objects.attacks;

import com.spellrush.business.LevelManager;

/**
 * AttackFactory
 *
 * Static class used to add attacks to the current game board.
 */
public class AttackFactory {

    public static final int ATTACK_SPEED = 25;
    public static final int ATTACK_DAMAGE = 10;

    /**
     * @param isPlayerAttack The attack's allegiance (and direction of movement).
     * @param laneIndex The lane the attack will start in.
     */
    public static void createFireAttack(boolean isPlayerAttack, int laneIndex){
        GameBoard board = LevelManager.getInstance().getGameBoard();
        board.addAttack(AttackObject.AttackType.Fire, isPlayerAttack, laneIndex, ATTACK_SPEED, ATTACK_DAMAGE);
    }

    /**
     * @param isPlayerAttack The attack's allegiance (and direction of movement).
     * @param laneIndex The lane the attack will start in.
     */
    public static void createWaterAttack(boolean isPlayerAttack, int laneIndex){
        GameBoard board = LevelManager.getInstance().getGameBoard();
        board.addAttack(AttackObject.AttackType.Water, isPlayerAttack, laneIndex, ATTACK_SPEED, ATTACK_DAMAGE);
    }

    /**
     * @param isPlayerAttack The attack's allegiance (and direction of movement).
     * @param laneIndex The lane the attack will start in.
     */
    public static void createGroundAttack(boolean isPlayerAttack, int laneIndex){
        GameBoard board = LevelManager.getInstance().getGameBoard();
        board.addAttack(AttackObject.AttackType.Ground, isPlayerAttack, laneIndex, ATTACK_SPEED, ATTACK_DAMAGE);
    }
}
