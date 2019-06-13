package com.spellrush.presentation.UI.Components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class ScoreDisplay {
    private final int NUM_DIGITS = 6;
    private int x;
    private int y;

    private Paint myPaint;

    public ScoreDisplay(int xpos, int ypos){
        x = xpos;
        y = ypos;
        myPaint = new Paint();
    }

    public void drawScore(Canvas canvas, int playerScore) {
        String formattedScore =  String.format("%0" + NUM_DIGITS + "d", playerScore);
        myPaint.setTextSize(64);
        myPaint.setColor(Color.WHITE);
        canvas.drawText("SCORE: " + formattedScore, x, y, myPaint);
    }
}
