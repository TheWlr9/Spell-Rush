package com.spellrush.objects.attacks;

import android.graphics.Canvas;

import com.spellrush.R;

public class WaterAttack extends AttackObject {

    /**
     * Constructor
     *
     * package scope only! - only GameBoard may add attacks to the screen
     *
     * @param attackInfo information about this attack
     * @param y Y Position to create the attack at
     */
    WaterAttack(AttackInformation attackInfo, int y) {
        super(attackInfo, AttackType.Water, y);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas, R.drawable.water_attack,R.drawable.water_attack_enemy);
    }
}
