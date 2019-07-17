package com.spellrush.objects;

import com.spellrush.business.IEnemyAI;
import com.spellrush.objects.attacks.AttackFactory;

import java.util.Random;

public class HardEnemyAI implements IEnemyAI {


    private final int MAX_WAIT=100; // the most amount of frames between attacks
    private final int MIN_WAIT=15; // the smallest amount of frames...
    private int frameCount=0;
    private int wait=30; //default wait between attacks in frames
    private int multiShot = 1;
    private final int MIN_SHOTS = 2;
    private final int MAX_SHOTS = 4;
    private AttackFactory.AttackType[] attacks;

    public HardEnemyAI(){
        attacks = AttackFactory.getAttacks();
    }

    @Override
    public void update() {
        frameCount+=1;
        if(frameCount>=wait){
            if(multiShot>1){
                wait = 10;
                multiShot-=1;
            }
            else{
                newWait();
                multiShot= (new Random()).nextInt(MAX_SHOTS-1)+MIN_SHOTS;
            }

            doAttack();
            frameCount=0;
        }
    }

    @Override
    public void doAttack() {
        int chooseAttack= (new Random()).nextInt(attacks.length);
        AttackFactory.createAttack(attacks[chooseAttack],false,1);
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

