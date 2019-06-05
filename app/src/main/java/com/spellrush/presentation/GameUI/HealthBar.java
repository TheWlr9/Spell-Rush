package com.spellrush.presentation.GameUI;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class HealthBar {

    private static int border_size = 5;

    private int x;
    private int y;
    private int width;
    private int height;

    public HealthBar(int xpos, int ypos, int r_width, int r_height){
        x = xpos;
        y = ypos;
        width = r_width;
        height = r_height;
    }

    public void drawHealthBar(Canvas canvas, int playerMaxHP, int playerHP) {
        int innerWidth = width * ( playerHP / playerMaxHP );
        onDraw(canvas, innerWidth);
    }

    private void onDraw(Canvas canvas, int innerWidth) {


        Paint myPaint = new Paint();
        myPaint.setColor(Color.BLACK);
        canvas.drawRect(x-border_size,y-border_size,x+width+border_size,y+height+border_size,myPaint);
        myPaint.setColor(Color.GREEN);
        canvas.drawRect(x,y,x+hpWidth,y+height,myPaint);
    }
}
