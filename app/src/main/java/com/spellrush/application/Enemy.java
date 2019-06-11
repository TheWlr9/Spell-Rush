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
    private final int ATTACK_WAIT=5;
    private int attackTimer;
    //private boolean alive=true;
    //private Paint myPaint;
    //private int col;

    public Enemy(){
        super(101);
        xPos= 500;
        yPos=250;
        attackTimer= (int) (System.currentTimeMillis()/1000);
        //myPaint.setColor(Color.BLUE);
        //col=myPaint.getColor();
    }
    @Override
    public void update() {
        if((System.currentTimeMillis()/1000)-attackTimer >=ATTACK_WAIT){
            attackTimer=(int)(System.currentTimeMillis()/1000);
            this.doAttack();
        }
    }

    private void doAttack(){
        //ExampleBall attack = new ExampleBall(xPos,yPos);
        //GameView.createObject(attack);
        //col=(col*2)%256;
       // myPaint.setColor(col);

    }

    public void getHit(){
        hp-=1;
        if(hp<=0){
            this.destroy();
        }
    }

    private void destroy(){
     //   alive=false;
    }
    @Override
    public void draw(Canvas canvas) {
        Paint myPaint = new Paint();
        myPaint.setColor(Color.BLUE);
        canvas.drawRect(xPos-enemyHalfWidth,yPos-enemyHalfHeight,xPos+enemyHalfWidth,yPos+enemyHalfHeight,myPaint);

        }
    }

