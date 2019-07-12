package com.spellrush.objects.attacks;

import com.spellrush.business.PlayerController;

public class AttackCollisionUtility {

    public static final int COLLISION_DIST = 100;
    static final int SMALL_POINTS = 50;
    static final int BIG_POINTS = SMALL_POINTS * 2;

    public static boolean areColliding(AttackObject attack1, AttackObject attack2, int distance){
        return Math.abs(attack1.getYPosition() - attack2.getYPosition()) <= distance;
    }

    /**
     * tells you whether first attack type beats other attack type
     *
     * @param ourType attackType of "us"
     * @param enemyType attackType of "them"
     * @return true if ourType should win, else false (possibility of tie)
     */
    public static boolean checkCollisionWinner(AttackObject.AttackType ourType, AttackObject.AttackType enemyType){
        //return whether first type beats second type.
        return (ourType == AttackObject.AttackType.Water && enemyType == AttackObject.AttackType.Fire) ||
                (ourType == AttackObject.AttackType.Ground && enemyType == AttackObject.AttackType.Water) ||
                (ourType == AttackObject.AttackType.Fire && enemyType == AttackObject.AttackType.Ground);
    }

    public static void handleCollision(AttackObject attackA, AttackObject attackB, PlayerController player){
        AttackObject.AttackType aType = attackA.getType(); //type of other attack
        AttackObject.AttackType bType = attackB.getType();
        AttackObject loser = null;

        //when two attacks from separate teams have collided, destroy the one of a
        // "weaker" type, unless they have the same type (destroy both)
        if (!attackA.wasDestroyed() && !attackB.wasDestroyed()
                && !attackA.hasSameAllegiance(attackB)) {
            if(aType == bType){
                attackA.setDestroyed(true);
                attackB.setDestroyed(true);
                player.addScore(SMALL_POINTS);
            }
            else {
                loser = checkCollisionWinner(aType, bType) ? attackB : attackA;
                loser.setDestroyed(true);
                if (!loser.isPlayerAttack()) {
                    player.addScore(BIG_POINTS);
                }
            }
        }
    }
}