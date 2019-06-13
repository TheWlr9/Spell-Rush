package com.spellrush.presentation.UI;

import android.content.res.Resources;
import android.graphics.Canvas;

import com.spellrush.business.GameView;
import com.spellrush.objects.GameObject;
import com.spellrush.business.PlayerController;
import com.spellrush.presentation.UI.Components.HealthBar;
import com.spellrush.presentation.UI.Components.ScoreDisplay;

public class GameHUD extends GameObject {
    private static final int HUD_DEPTH = -100;
    private HealthBar hpUI;
    private ScoreDisplay score;
    private PlayerController player;

    public GameHUD(PlayerController player) {
        super(HUD_DEPTH);
        this.player = player;

        int deviceWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int deviceHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        hpUI = new HealthBar(50,deviceHeight - 100,deviceWidth - 75,50, 15);
        score = new ScoreDisplay(deviceWidth - 475, deviceHeight - 130);
    } // end constructor method

    @Override
    public void update(){
        // Nothing to do yet.
    } // end update()

    @Override
    public void draw(Canvas canvas){
        if(canvas != null) {
            hpUI.drawHealthBar(canvas, player.MAX_HP, player.getHP());
            score.drawScore(canvas, player.getScore());
        }
    } // end draw()
} // end GameHudView class
