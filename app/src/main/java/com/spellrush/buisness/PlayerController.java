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
        if( amt > 0 ) {
            hp -= amt;
        }
        if(hp < 0){
            hp = 0;
        }
        return hp;
    } // end loseHP()

    public int addHP( int amt) {
        if( amt > 0 ) {
            hp += amt;
        }
        if(hp > MAX_HP){
            hp = MAX_HP;
        }
        return hp;
    } // end addHP()

    public void update(){
    } // end update()

} // end PlayerController class
