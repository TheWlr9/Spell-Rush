package com.spellrush.buisness;

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

    public int loseHP(int amt) {
        hp = (hp - amt) < 0 ? 0: hp - amt;
        return hp;
    }

    public int addHP( int amt) {
        hp = (hp + amt) > MAX_HP ? MAX_HP: hp + amt;
        return hp;
    }

    public void update(){
    }

} // end PlayerController class
