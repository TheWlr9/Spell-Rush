package com.spellrush.objects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class ExampleBall extends GameObject {
    private int xPos;
    private int yPos;

    public ExampleBall(){
        super(100);
        xPos = 400;
        yPos = 0;
    }

    @Override
    public void update() {
        yPos++;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint myPaint = new Paint();
        myPaint.setColor(Color.RED);
        canvas.drawCircle(xPos,yPos,100,myPaint);
    }
}
