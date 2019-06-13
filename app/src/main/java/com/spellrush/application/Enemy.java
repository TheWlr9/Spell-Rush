package com.spellrush.application;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.spellrush.business.GameView;
import com.spellrush.objects.GameObject;

public class Enemy extends GameObject {

    public final int MAX_HP = 100;
    private final int enemyHalfWidth = 100;
    private final int enemyHalfHeight = 50;

    private int hp;
    private int xPos;
    private int yPos;
    private int attackWait; //30FPS. So one second for every 30
    private int attackTimer;
    private boolean alive;

    public Enemy(int x, int y, int depth , int framesBetweenAttacks){
        super(depth);
        xPos = x;
        yPos = y;
        attackWait = framesBetweenAttacks;
        attackTimer = 0;
        hp = MAX_HP;
        alive = true;

    }

    public int getHP(){
        return this.hp;
    }

    @Override
    public void update() {
        attackTimer+=1;
        if(attackTimer >=attackWait){
            attackTimer=0;
            this.doAttack();
        }
    }

    private void doAttack(){
        // Op
    }

    public void getHit(int dmg){
        if(dmg > 0) {
            hp -= dmg;
            if (hp <= 0) {
                hp = 0;
                this.destroy();
            }
        }
    }

    public boolean isAlive(){
        return alive;
    }

    private void destroy(){
        alive=false;
        GameView.removeObject(this);
    }

    @Override
    public void draw(Canvas canvas) {
        Paint myPaint = new Paint();
        myPaint.setColor(Color.BLUE);
        canvas.drawRect(xPos-enemyHalfWidth,yPos-enemyHalfHeight,
                xPos+enemyHalfWidth,yPos+enemyHalfHeight,myPaint);

        }
    }

