package com.spellrush.objects.attacks;

import com.spellrush.audio.AudioManager;
import com.spellrush.audio.AudioManagerError;
import com.spellrush.audio.SoundEvent;
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
    public static boolean checkCollisionWinner(AttackObject ourType, AttackObject enemyType){
        //return whether first type beats second type.
        return (ourType instanceof WaterAttack && enemyType instanceof FireAttack) ||
                (ourType instanceof GroundAttack && enemyType instanceof WaterAttack) ||
                (ourType instanceof FireAttack && enemyType instanceof GroundAttack);
    }

    public static void handleCollision(AttackObject attackA, AttackObject attackB, PlayerController player){
        AttackObject loser = null;

        //when two attacks from separate teams have collided, destroy the one of a
        // "weaker" type, unless they have the same type (destroy both)
        if (!attackA.wasDestroyed() && !attackB.wasDestroyed()
                && !attackA.hasSameAllegiance(attackB)) {
            try{
                AudioManager.play(SoundEvent.SPELLS_COLLIDED, true);
            }
            catch(AudioManagerError ame){
                System.err.println("Error playing SPELLS_COLLIDED; non-linked file");
            }

            if(attackA.getClass() == attackB.getClass()){
                attackA.setDestroyed(true);
                attackB.setDestroyed(true);
                player.addScore(SMALL_POINTS);
            }
            else {
                loser = checkCollisionWinner(attackA, attackB) ? attackB : attackA;
                loser.setDestroyed(true);
                if (!loser.isPlayerAttack()) {
                    player.addScore(BIG_POINTS);
                }
            }
        }
    }
}