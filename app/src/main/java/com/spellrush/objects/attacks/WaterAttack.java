package com.spellrush.objects.attacks;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.spellrush.R;
import com.spellrush.business.GameView;

public class WaterAttack extends AttackObject {

    /**
     * Constructor
     *
     * package scope only! - only GameBoard may add attacks to the screen
     *
     * @param attackInfo information about this attack
     */
    WaterAttack(AttackInformation attackInfo) {
        super(attackInfo, AttackType.Water);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas, R.drawable.water_attack,R.drawable.water_attack_enemy);
    }
}
