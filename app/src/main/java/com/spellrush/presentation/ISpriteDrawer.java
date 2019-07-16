package com.spellrush.presentation;

import android.graphics.Canvas;

public interface ISpriteDrawer {

    /**
     * Draws the given sprite index in resources>Drawable to the given canvas.
     * Draws the sprite from the top left at coordinates (x,y)
     *
     * @param canvas canvas to draw on
     * @param spriteIndex sprite to draw
     * @param x X position to draw from
     * @param y Y position to draw from
     * @param w Width to draw the sprite
     * @param h Height to draw the sprite
     */
    void drawSprite(Canvas canvas, int spriteIndex, int x, int y, int w, int h);

    /**
     * Draws the given sprite index in resources>Drawable to the given canvas.
     * Draws the sprite centered at coordinates (x,y)
     *
     * @param canvas canvas to draw on
     * @param spriteIndex sprite to draw
     * @param x X position to draw at
     * @param y Y position to draw at
     * @param w Width to draw the sprite
     * @param h Height to draw the sprite
     */
    void drawSpriteCentered(Canvas canvas, int spriteIndex, int x, int y, int w, int h);
}
