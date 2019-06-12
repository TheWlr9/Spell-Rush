package com.spellrush.business;

/*******************************************
 * Player Controller Class
 *
 * Keep track of player HP, Score,
 * Learned spells, etc.
 * *****************************************/
public class PlayerController {
    public static final int MAX_HP = 100;
    private int hp;

    public PlayerController() {
        hp = MAX_HP;
    }

    public int getHP(){
        return this.hp;
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
    
    public void update(){
    }

} // end PlayerController class
