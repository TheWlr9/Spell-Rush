package com.spellrush.presentation.Views.UI;

import android.graphics.Canvas;

import com.spellrush.application.GameObject;
import com.spellrush.buisness.PlayerController;
import com.spellrush.presentation.Views.Components.HealthBar;

public class GameHUD extends GameObject
{
    private static final int HUD_DEPTH = -100;
    private PlayerController player; //todo: Move Player somewhere logical, and pass in HP
    private HealthBar hpUI;

    public GameHUD(){
        super(HUD_DEPTH);
        player = new PlayerController();
        hpUI = new HealthBar(20,100,900,50, 15);
    } // end constructor method

    @Override
    public void update(){
        player.update();
    } // end update()

    @Override
    public void draw(Canvas canvas){
        if(canvas != null) {
            hpUI.drawHealthBar(canvas, player.MAX_HP, player.getHP());
        }
    } // end draw()
} // end GameHudView class
