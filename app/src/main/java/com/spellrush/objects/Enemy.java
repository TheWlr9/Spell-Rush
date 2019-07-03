package com.spellrush.objects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.spellrush.business.IEnemyAI;

public class Enemy extends HealthObject {

    public static final Enemy instance = new Enemy();

    private static final int MAX_HP = 100;
    protected static boolean alive;
    protected static IEnemyAI brain = new NullEnemyAI();


    private Enemy(){
        super(0, MAX_HP);
        alive = true;
    }

    public void setAI(IEnemyAI a_i_Type){
        brain  = a_i_Type;
    }

    public IEnemyAI getAI(){
        return brain;
    }

    public static Enemy getInstance(){
        return  instance;
    }
    public boolean isAlive(){
        return alive;
    }

    @Override
    public void update() {
    brain.update();
    }

    @Override
    protected void onDestroyed(){
        alive=false;
        setAI( new NullEnemyAI());
    }

    @Override
    public void draw(Canvas canvas) {
        Paint myPaint = new Paint();
        myPaint.setColor(Color.BLUE);
        canvas.drawRect(0,0, 2000,200, myPaint);
    }

}

