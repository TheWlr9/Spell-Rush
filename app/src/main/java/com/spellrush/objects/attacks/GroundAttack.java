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
     */
    GroundAttack(AttackInformation attackInfo) {
        super(attackInfo, AttackType.Ground);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas, R.drawable.ground_attack,R.drawable.ground_attack_enemy);
    }
}
