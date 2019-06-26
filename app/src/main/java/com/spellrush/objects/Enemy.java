package com.spellrush.objects;

import android.graphics.Canvas;

public abstract class Enemy extends HealthObject {
    protected int xPos;
    protected int yPos;

    protected int attackWait; //30FPS. So one second for every 30
    protected int attackTimer;
    protected boolean alive;


    public Enemy(int x, int y, int depth , int framesBetweenAttacks, int maxHP){
        super(depth, maxHP);
        xPos = x;
        yPos = y;
        attackWait = framesBetweenAttacks;
        attackTimer = 0;
        alive = true;
    }

    private void doAttack(){
        // Op
    }

    public boolean isAlive(){
        return alive;
    }

    @Override
    public void update() {
        attackTimer+=1;
        if(attackTimer >=attackWait){
            attackTimer=0;
            this.doAttack();
        }
    }

    @Override
    protected void onDestroyed(){
        alive=false;
    }

    @Override
    public abstract void draw(Canvas canvas);

}

