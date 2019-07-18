package com.spellrush.objects;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.spellrush.R;
import com.spellrush.business.GameThread;
import com.spellrush.business.GameView;
import com.spellrush.business.IEnemyAI;

public class Enemy extends HealthObject {

    public static final Enemy instance = new Enemy();
    public static final int Y_POSITION = 250;
    public static final int SPRITE_WIDTH = 360;
    public static final int SPRITE_HEIGHT = 328;

    private static final int MAX_HP = 30;
    private static final int ANIMATION_SPEED = GameThread.FRAMES_PER_SECOND / 2;

    protected static boolean alive;
    protected static IEnemyAI brain = new NullEnemyAI();

    private int drawTimer;
    private boolean frame;
    private Drawable spriteFrame1;
    private Drawable spriteFrame2;
    private Drawable currSprite = null;

    private Enemy(){
        super(0, MAX_HP);
        init();
    }

    // Do things that should be done on level start
    private void init(){
        alive = true;
        drawTimer = 0;
        frame = false;
    }

    private void initSprite(){
        Resources r = GameView.getInstance().getContext().getResources();
        spriteFrame1 = r.getDrawable(R.drawable.enemy_0);
        spriteFrame2 = r.getDrawable(R.drawable.enemy_1);
        int spriteX = Resources.getSystem().getDisplayMetrics().widthPixels / 2;
        spriteFrame1.setBounds(spriteX - (SPRITE_WIDTH / 2), Y_POSITION - (SPRITE_HEIGHT / 2),
                spriteX + (SPRITE_WIDTH / 2), Y_POSITION + (SPRITE_HEIGHT / 2));
        spriteFrame2.setBounds(spriteFrame1.getBounds());
        currSprite = spriteFrame1;
    }

    public void setAI(IEnemyAI a_i_Type){
        brain  = a_i_Type;
    }

    public IEnemyAI getAI(){
        return brain;
    }
    int getDrawTimer(){return drawTimer;}
    public static Enemy getInstance(){
        return  instance;
    }
    public boolean isAlive(){
        return alive;
    }

    public void reset(){
        init();
        super.addHP(MAX_HP);
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
    public void update() {
        brain.update();
        drawTimer = updateDrawTimer(drawTimer);
    }

    @Override
    protected void onDestroyed(){
        alive=false;
        setAI( new NullEnemyAI());
    }

    @Override
    public void draw(Canvas canvas) {
        if(currSprite==null){initSprite();}
        currSprite.draw(canvas);
    }

}

