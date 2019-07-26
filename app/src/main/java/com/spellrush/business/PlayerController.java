package com.spellrush.business;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.spellrush.R;
import com.spellrush.objects.HealthObject;

/*******************************************
 * Player Controller Class
 *
 * Keep track of player HP, Score,
 * Learned spells, etc.
 * *****************************************/
public class PlayerController extends HealthObject {

    public static final int SPRITE_WIDTH = 360;
    public static final int SPRITE_HEIGHT = 328;
    public static final int Y_POSITION = Resources.getSystem().getDisplayMetrics().heightPixels - SPRITE_HEIGHT;

    private static final int ANIMATION_SPEED = GameThread.FRAMES_PER_SECOND / 2;

    // Follow Singleton design pattern
    private static final PlayerController instance = new PlayerController();

    public static final int MAX_HP = 30;
    private int score;
    private int drawTimer;
    private boolean frame;
    private Drawable spriteFrame1;
    private Drawable spriteFrame2;
    private Drawable currSprite = null;

    private PlayerController() {
        super(0, MAX_HP);
        score = 0;
        drawTimer = 0;
    }

    public static PlayerController getInstance(){
        return instance;
    }

    @Override
    protected void onDestroyed() {
        GameView.getInstance().triggerGameOver();
        return;
    }

    public int getScore(){
        return score;
    }

    public void addScore(int amt){
        if(amt > 0)
            score += amt;
    }

    public void reset(){
        super.addHP(MAX_HP);
        score = 0;
    }

    private void initSprite(){
        Resources r = GameView.getInstance().getContext().getResources();
        spriteFrame1 = r.getDrawable(R.drawable.player2);
        spriteFrame2 = r.getDrawable(R.drawable.player1);
        int spriteX = Resources.getSystem().getDisplayMetrics().widthPixels / 2;
        spriteFrame1.setBounds(spriteX - (SPRITE_WIDTH / 2), Y_POSITION - (SPRITE_HEIGHT / 2),
                spriteX + (SPRITE_WIDTH / 2), Y_POSITION + (SPRITE_HEIGHT / 2));
        spriteFrame2.setBounds(spriteFrame1.getBounds());
        currSprite = spriteFrame1;
    }

    private int updateDrawTimer(int drawTime){
        drawTime++;
        if(drawTime > ANIMATION_SPEED){
            drawTime = 0;
            frame = !frame;
            currSprite = frame ? spriteFrame1 : spriteFrame2;
        }
        return drawTime;
    }

    @Override
    public void update(){
        drawTimer = updateDrawTimer(drawTimer);
    }

    @Override
    public void draw(Canvas canvas) {
        if(currSprite==null){initSprite();}
        currSprite.draw(canvas);
    }

} // end PlayerController class
