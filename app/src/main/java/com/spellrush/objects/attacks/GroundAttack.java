package com.spellrush.objects.attacks;

import android.graphics.Canvas;

import com.spellrush.R;
import com.spellrush.business.GameThread;

public class GroundAttack extends AttackObject {
    private static final int ANIMATION_SPEED = GameThread.FRAMES_PER_SECOND / 4;

    private int drawTimer;
    private boolean frame;
    private final int FRIEND_SPRITE_ID_FRAME_1 = R.drawable.ground_attack1;
    private final int FRIEND_SPRITE_ID_FRAME_2 = R.drawable.ground_attack2;
    private final int ENEMY_SPRITE_ID_FRAME_1 = R.drawable.ground_attack_enemy1;
    private final int ENEMY_SPRITE_ID_FRAME_2 = R.drawable.ground_attack_enemy2;
    private int friendCurrSpriteID = FRIEND_SPRITE_ID_FRAME_1;
    private int enemyCurrSpriteID = ENEMY_SPRITE_ID_FRAME_1;

    /**
     * Constructor
     *
     * package scope only! - only GameBoard may add attacks to the screen
     *
     * @param attackInfo information about this attack
     * @param y Y Position to create the attack at
     */
    GroundAttack(AttackInformation attackInfo, int y) {
        super(attackInfo, y);
    }

    private int updateDrawTimer(int drawTime){
        drawTime++;

        if(drawTime > ANIMATION_SPEED){
            frame = !frame;

            drawTime = 0;

            friendCurrSpriteID = frame ? FRIEND_SPRITE_ID_FRAME_1 : FRIEND_SPRITE_ID_FRAME_2;
            enemyCurrSpriteID = frame ? ENEMY_SPRITE_ID_FRAME_1 : ENEMY_SPRITE_ID_FRAME_2;
        }

        return drawTime;
    }

    @Override
    public void update(){
        super.update();

        drawTimer = updateDrawTimer(drawTimer);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas, friendCurrSpriteID, enemyCurrSpriteID);
    }
}
