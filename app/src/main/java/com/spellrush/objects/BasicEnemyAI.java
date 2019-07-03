package com.spellrush.objects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.spellrush.business.IEnemyAI;
import com.spellrush.objects.attacks.AttackFactory;

import java.util.Random;

public class BasicEnemyAI implements IEnemyAI {

    private final int MAX_WAIT=120; // the most amount of frames between attacks
    private final int MIN_WAIT=30; // the smallest amount of frames...
    private int frameCount=0;
    private int wait=30; //default wait between attacks in frames

    @Override
    public void doAttack() {
        int chooseAttack= (new Random()).nextInt(3);
        switch(chooseAttack ){
            case 0:AttackFactory.createFireAttack(false,0);break;
            case 1:AttackFactory.createWaterAttack(false,0);break;
            case 2:AttackFactory.createGroundAttack(false,0);break;
        }
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

    public void newWait(){
        wait = (new Random()).nextInt(MAX_WAIT-MIN_WAIT+1)+MIN_WAIT;
    }

}

