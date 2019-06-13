package com.spellrush.business;

import android.graphics.Canvas;

import com.spellrush.objects.GameObject;

/*******************************************
 * Player Controller Class
 *
 * Keep track of player HP, Score,
 * Learned spells, etc.
 * *****************************************/
public class PlayerController extends GameObject {
    public static final int MAX_HP = 100;
    private int hp;
    private int score;

    public PlayerController() {
        super(0);
        hp = MAX_HP;
        score = 0;
    }

    public int getHP(){
        return this.hp;
    }
    public int getScore(){
        return score;
    }

    // Reduce player HP by amount
    public int loseHP(int amount) {
        if(amount > 0) { // edge case: amount is negative
            hp = Math.max(hp - amount, 0);
        }
        return hp;
    }


    // Increase player HP by amount
    public int addHP( int amount) {
        if(amount > 0) { // edge case: amount is negative
            hp = Math.min(hp + amount, MAX_HP);
        }
        return hp;
    }

    public void addScore(int amt){
        if(amt > 0)
            score += amt;
    }

    public void update(){
        addScore(1);
    }

    @Override
    public void draw(Canvas canvas) { return; }

} // end PlayerController class
