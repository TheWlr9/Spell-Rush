package com.spellrush.objects.attacks;

import android.graphics.Canvas;

import com.spellrush.R;

public class GroundAttack extends AttackObject {

    /**
     * Constructor
     *
     * package scope only! - only GameBoard may add attacks to the screen
     *
     * @param attackInfo information about this attack
     * @param y Y Position to create the attack at
     */
    GroundAttack(AttackInformation attackInfo, int y) {
        super(attackInfo, AttackType.Ground, y);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas, R.drawable.ground_attack,R.drawable.ground_attack);
    }
}
