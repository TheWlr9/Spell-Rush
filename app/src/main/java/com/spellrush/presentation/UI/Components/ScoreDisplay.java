package com.spellrush.presentation.UI.Components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.spellrush.business.PlayerController;
import com.spellrush.objects.IDrawnObject;

public class ScoreDisplay implements IDrawnObject {
    private final int NUM_DIGITS = 6;
    private final String scoreTxt = "SCORE";
    private String formattedScore;

    private int x;
    private int y;

    private Paint myPaint;

    public ScoreDisplay(int xpos, int ypos){
        x = xpos;
        y = ypos;
        myPaint = new Paint();
        myPaint.setTextSize(64);
        myPaint.setColor(Color.WHITE);
        formattedScore =  "";
    }

    public void draw(Canvas canvas) {
        canvas.drawText(scoreTxt + formattedScore, x, y, myPaint);
    }

    public void updatescoreTxt(){
        int playerScore = PlayerController.getInstance().getScore();
        formattedScore =  String.format("%0" + NUM_DIGITS + "d", playerScore);
    }
}
