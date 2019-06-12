package com.spellrush.application;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.spellrush.buisness.GameView;

public class Enemy extends GameObject {

    private int hp = 3; //magic for now. Decide on difficulty levels later
    private int xPos;
    private int yPos;
    private int enemyHalfWidth=100;
    private int enemyHalfHeight=50;
    private int attackWait=30;//30FPS. So one second for every 30
    private int attackTimer;
    private boolean alive=true;
    private int col;

    public Enemy(int x, int y, int depth , int framesBetweenAttacks){
        super(depth);
        xPos= x;
        yPos=y;
        attackWait=framesBetweenAttacks;
        attackTimer= 0;

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
        System.out.println("attack done");
    }

    public void getHit(){
        hp-=1;
        if(hp<=0){
            this.destroy();
        }
    }

    private void destroy(){
        alive=false;
    }
    @Override
    public void draw(Canvas canvas) {
        Paint myPaint = new Paint();
        myPaint.setColor(Color.BLUE);
        canvas.drawRect(xPos-enemyHalfWidth,yPos-enemyHalfHeight,
                xPos+enemyHalfWidth,yPos+enemyHalfHeight,myPaint);

        }
    }

