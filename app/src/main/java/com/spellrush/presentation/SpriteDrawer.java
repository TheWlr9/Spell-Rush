package com.spellrush.presentation;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.spellrush.business.GameView;

public class SpriteDrawer implements ISpriteDrawer {

    // Follow singleton pattern since interfaces can not have static methods in API level 19
    // (Otherwise this would just be a static class, with a static draw method)
    private static SpriteDrawer instance = null;

    private SpriteDrawer(){ }

    public static SpriteDrawer getInstance(){
        if(instance == null){
            instance = new SpriteDrawer();
        }
        return instance;
    }

    public void drawSprite(Canvas canvas, int spriteIndex, int x, int y, int w, int h){
        Drawable sprite = getSpriteByIndex(spriteIndex);
        sprite.setBounds(x, y, x + w, y + h);
        sprite.draw(canvas);
    }

    public void drawSpriteCentered(Canvas canvas, int spriteIndex, int x, int y, int w, int h){
        Drawable sprite = getSpriteByIndex(spriteIndex);
        sprite.setBounds(x - (w/2), y - (h/2), x + (w/2), y + (h/2));
        sprite.draw(canvas);
    }

    private Drawable getSpriteByIndex(int spriteIndex){
        Resources r = GameView.getInstance().getContext().getResources();
        return r.getDrawable(spriteIndex);
    }
}
