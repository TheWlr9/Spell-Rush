package com.spellrush.objects.attacks;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.spellrush.R;
import com.spellrush.business.GameView;

public class FireAttack extends AttackObject {

    /**
     * Constructor
     *
     * package scope only! - only GameBoard may add attacks to the screen
     *
     * @param attackInfo information about this attack
     */
    FireAttack(AttackInformation attackInfo) {
        super(attackInfo, AttackType.Fire);
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
