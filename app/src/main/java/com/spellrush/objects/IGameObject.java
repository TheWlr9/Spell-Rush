package com.spellrush.objects;

import android.graphics.Canvas;

public interface IGameObject extends IDrawnObject {
    // Called once per frame by the GameView.
    void update();

    // Draw to the canvas.
    void draw(Canvas canvas);
}
