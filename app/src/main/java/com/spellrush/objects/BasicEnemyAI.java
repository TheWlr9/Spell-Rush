package com.spellrush.objects;

import com.spellrush.business.IEnemyAI;
import com.spellrush.objects.attacks.AttackFactory;

import java.util.Random;

public class BasicEnemyAI implements IEnemyAI {

    final int MAX_WAIT=120; // the most amount of frames between attacks
    final int MIN_WAIT=30; // the smallest amount of frames...

    private int frameCount=0;
    private int wait=30; //default wait between attacks in frames

    private AttackFactory.AttackType[] attacks;

    public BasicEnemyAI(){
        attacks = AttackFactory.getAttacks();
    }


    @Override
    public void doAttack() {
        int chooseAttack= (new Random()).nextInt(attacks.length);
        AttackFactory.createAttack(attacks[chooseAttack],false,1);
    }

    @Override
    public void update() {
        frameCount+=1;
        if(frameCount>=wait){
            newWait();
            doAttack();
            frameCount=0;
        }
    }

    private void newWait(){
        wait = (new Random()).nextInt(MAX_WAIT-MIN_WAIT+1)+MIN_WAIT;
    }

    // package-scoped getters for testing, etc.
    int getWait(){
        return wait;
    }
    int getFrameCount() {
        return frameCount;
    }
}

