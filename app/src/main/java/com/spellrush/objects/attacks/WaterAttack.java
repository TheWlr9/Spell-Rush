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
     * @param isPlayerAttack whether this is a player attack
     * @param lane which lane this belongs to
     * @param speed what speed this attack will travel at
     * @param laneStart Y value of the bullet's spawning position
     * @param laneEnd Y value of the "goal zone"
     * @param damage How much is subtracted from the enemy/player HP on goal
     */
    WaterAttack(boolean isPlayerAttack, int lane, int speed, int laneStart, int laneEnd, int damage) {
        super(isPlayerAttack, lane, speed, laneStart, laneEnd, damage, AttackType.Water);
    }

    @Override
    public void draw(Canvas canvas) {
        Resources r = GameView.getInstance().getContext().getResources();
        Drawable spr;
        if(isPlayerAttack) {
            spr = r.getDrawable(R.drawable.water_attack);
        } else {
            spr = r.getDrawable(R.drawable.water_attack_enemy);
        }

        spr.setBounds(xPos, yPos, xPos + super.ATTACK_WIDTH, yPos + super.ATTACK_HEIGHT);
        spr.draw(canvas);
    }
}
