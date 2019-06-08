package com.spellrush.presentation.Views.Layers;

import android.content.Context;
import android.graphics.Canvas;

import com.spellrush.buisness.PlayerController;
import com.spellrush.presentation.Views.Components.HealthBar;

public class GameViewHUDLayer extends GameViewLayer
{
    private PlayerController player; //todo: Move Player somewhere logical, and pass in HP
    private HealthBar hpUI;

    public GameViewHUDLayer(Context context){
        super(context);
        player = new PlayerController();
        hpUI = new HealthBar(20,100,900,50, 15);
        getHolder().addCallback(this);
        setFocusable(true);
    } // end constructor method

    @Override
    public void update(){
        player.update();
    } // end update()

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        if(canvas != null) {
            hpUI.drawHealthBar(canvas, player.MAX_HP, player.getHP());
        }
    } // end draw()
} // end GameHudView class
