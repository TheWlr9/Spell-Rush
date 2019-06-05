package com.spellrush.presentation.Views.Components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class HealthBar {

    private int x;
    private int y;
    private int width;
    private int height;
    private int borderWidth;

    private Paint myPaint;

    public HealthBar(int xpos, int ypos, int width, int height, int borderWidth){
        x = xpos;
        y = ypos;
        this.width = width;
        this.height = height;
        this.borderWidth = borderWidth;

        myPaint = new Paint();
    }

    public void drawHealthBar(Canvas canvas, int playerMaxHP, int playerHP) {
        int innerWidth = (int) Math.round(width * ((double) playerHP / playerMaxHP));
        onDraw(canvas, innerWidth);
    }

    private void onDraw(Canvas canvas, int innerWidth) {

        myPaint.setTextSize(64);
        myPaint.setColor(Color.BLACK);
        canvas.drawRect(x-borderWidth,y-borderWidth,x+width+borderWidth,y+height+borderWidth,myPaint);

        myPaint.setColor(Color.GRAY);
        canvas.drawRect(x,y,x+width,y+height,myPaint);

        myPaint.setColor(Color.GREEN);
        canvas.drawRect(x,y,x+innerWidth,y+height,myPaint);

        myPaint.setColor(Color.WHITE);
        canvas.drawText("HEALTH", x, y - 32, myPaint);
    }
}
