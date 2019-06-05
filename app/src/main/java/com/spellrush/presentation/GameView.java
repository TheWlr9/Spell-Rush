package com.spellrush.presentation;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import com.spellrush.application.PlayerController;
import com.spellrush.presentation.Components.HealthBar;

public class GameView extends View {

    private PlayerController player;
    private HealthBar hpUI;


    public GameView(Context context){
        super(context);
        player = new PlayerController();
        hpUI = new HealthBar(20,100,900,50, 15);
    }

    public void update(){
        player.update();
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        if(canvas != null){
            hpUI.drawHealthBar(canvas, player.MAX_HP, 80);
        }
    }
}
