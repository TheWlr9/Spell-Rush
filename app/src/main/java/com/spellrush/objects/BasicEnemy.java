package com.spellrush.objects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.spellrush.objects.attacks.AttackFactory;

public class BasicEnemy extends Enemy {

    public static final int MAX_HP = 100;
    private final int enemyHalfWidth = 100;
    private final int enemyHalfHeight = 50;

    public BasicEnemy(int x, int y, int depth , int framesBetweenAttacks){
        super(x, y, depth, framesBetweenAttacks, MAX_HP);
    }

    @Override
    public void doAttack() {
        AttackFactory.createFireAttack(false,0);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void draw(Canvas canvas) {
        Paint myPaint = new Paint();
        myPaint.setColor(Color.BLUE);
        canvas.drawRect(xPos-enemyHalfWidth,yPos-enemyHalfHeight,
                xPos+enemyHalfWidth,yPos+enemyHalfHeight,myPaint);
    }

}

