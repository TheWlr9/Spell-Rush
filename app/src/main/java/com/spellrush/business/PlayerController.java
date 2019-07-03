package com.spellrush.business;

import android.graphics.Canvas;

import com.spellrush.objects.HealthObject;

/*******************************************
 * Player Controller Class
 *
 * Keep track of player HP, Score,
 * Learned spells, etc.
 * *****************************************/
public class PlayerController extends HealthObject {

    // Follow Singleton design pattern
    private static final PlayerController instance = new PlayerController();

    public static final int MAX_HP = 100;
    private int score;

    private PlayerController() {
        super(0, MAX_HP);
        score = 0;
    }

    public static PlayerController getInstance(){
        return instance;
    }

    @Override
    protected void onDestroyed() {
       GameView.getInstance().triggerGameOver();
       return;
    }

    public int getScore(){
        return score;
    }

    public void addScore(int amt){
        if(amt > 0)
            score += amt;
    }

    public void reset(){
        super.addHP(MAX_HP);
        score = 0;
    }

    @Override
    public void update(){
        // addScore(1);
    }

    @Override
    public void draw(Canvas canvas) { return; }

} // end PlayerController class
