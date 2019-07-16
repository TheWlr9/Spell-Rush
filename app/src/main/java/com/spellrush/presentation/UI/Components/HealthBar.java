package com.spellrush.presentation.UI.Components;

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
    private int hpColor;
    private boolean showText;

    private final String healthTxt = "HEALTH";

    public HealthBar(int xpos, int ypos, int width, int height, int borderWidth, boolean forPlayer) {
        x = xpos;
        y = ypos;
        this.width = width;
        this.height = height;
        this.borderWidth = borderWidth;

        myPaint = new Paint();
        if(forPlayer){
            hpColor = Color.GREEN;
            showText = true;
        }
        else {
            hpColor = Color.RED;
            showText = false;
        }
    }

    public void drawHealthBar(Canvas canvas, int baseHP, int maxHP) {
        int innerWidth = (int) Math.round(width * ((double) baseHP / maxHP));
        draw(canvas, innerWidth);
    }

    private void draw(Canvas canvas, int innerWidth) {
        myPaint.setTextSize(64);
        myPaint.setColor(Color.BLACK);
        canvas.drawRect(x-borderWidth,y-borderWidth,x+width+borderWidth,y+height+borderWidth,myPaint);

        myPaint.setColor(Color.GRAY);
        canvas.drawRect(x,y,x+width,y+height,myPaint);

        myPaint.setColor(hpColor);
        canvas.drawRect(x,y,x+innerWidth,y+height,myPaint);

        if(showText) {
            myPaint.setColor(Color.WHITE);
            canvas.drawText(healthTxt, x, y - 32, myPaint);
        }
    }
}
