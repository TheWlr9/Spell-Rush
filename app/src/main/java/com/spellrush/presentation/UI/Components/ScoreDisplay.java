package com.spellrush.presentation.UI.Components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.spellrush.business.PlayerController;
import com.spellrush.objects.IDrawnObject;

public class ScoreDisplay implements IDrawnObject {
    private final int NUM_DIGITS = 6;
    private int x;
    private int y;

    private Paint myPaint;

    public ScoreDisplay(int xpos, int ypos){
        x = xpos;
        y = ypos;
        myPaint = new Paint();
    }

    public void draw(Canvas canvas) {
        int playerScore = PlayerController.getInstance().getScore();
        String formattedScore =  String.format("%0" + NUM_DIGITS + "d", playerScore);
        myPaint.setTextSize(64);
        myPaint.setColor(Color.WHITE);
        canvas.drawText("SCORE: " + formattedScore, x, y, myPaint);
    }
}
