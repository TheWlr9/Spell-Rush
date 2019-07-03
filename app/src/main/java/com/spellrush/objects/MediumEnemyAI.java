package com.spellrush.objects;

import com.spellrush.business.IEnemyAI;
import com.spellrush.objects.attacks.AttackFactory;

import java.util.Random;

public class MediumEnemyAI implements IEnemyAI {

    private final int MAX_WAIT=120; // the most amount of frames between attacks
    private final int MIN_WAIT=30; // the smallest amount of frames...
    private int frameCount=0;
    private int wait=30; //default wait between attacks in frames
    private int multiShot = 1;
    private final int MAX_SHOTS = 3;

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
                multiShot= (new Random()).nextInt(MAX_SHOTS)+1;
            }

            doAttack();
            frameCount=0;
        }
    }

    @Override
    public void doAttack() {
        int chooseAttack= (new Random()).nextInt(3);
        switch(chooseAttack ){
            case 0:AttackFactory.createFireAttack(false,0);break;
            case 1:AttackFactory.createWaterAttack(false,0);break;
            case 2:AttackFactory.createGroundAttack(false,0);break;
        }
    }

    public void newWait(){
        wait = (new Random()).nextInt(MAX_WAIT-MIN_WAIT+1)+MIN_WAIT;
    }

}
