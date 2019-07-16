package com.spellrush.objects.attacks;

import android.graphics.Canvas;

import com.spellrush.R;

public class FireAttack extends AttackObject {

    /**
     * Constructor
     *
     * package scope only! - only GameBoard may add attacks to the screen
     *
     * @param attackInfo information about this attack
     * @param y Y Position to create the attack at
     */
    FireAttack(AttackInformation attackInfo, int y) {
        super(attackInfo, y);
    }

    @Override
    public void update(){
        super.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas, R.drawable.fire_attack,R.drawable.fire_attack_enemy);
    }
}
