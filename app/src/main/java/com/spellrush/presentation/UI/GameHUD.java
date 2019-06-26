package com.spellrush.presentation.UI;

import android.content.res.Resources;
import android.graphics.Canvas;

import com.spellrush.business.LevelManager;
import com.spellrush.business.PlayerController;
import com.spellrush.objects.Enemy;
import com.spellrush.objects.GameObject;
import com.spellrush.presentation.UI.Components.HealthBar;
import com.spellrush.presentation.UI.Components.ScoreDisplay;

public class GameHUD extends GameObject {

    public static final int HUD_DEPTH = -100;
    private HealthBar hpUI, enemyHpUI;
    private ScoreDisplay score;

    public GameHUD() {
        super(HUD_DEPTH);
        initializeElements();
    } // end constructor method

    private void initializeElements(){
        int deviceWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int deviceHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        int barIndent = 50;
        int barTopMargin = 100;
        int barHeight = 50;
        int barBorder = 15;

        hpUI = new HealthBar(barIndent,deviceHeight - barTopMargin,deviceWidth - 75,barHeight, barBorder, true);
        score = new ScoreDisplay(deviceWidth - 475, deviceHeight - 130);
        enemyHpUI = new HealthBar(barIndent,barTopMargin,deviceWidth - 75,barHeight, barBorder, false);
    }

    @Override
    public void update(){
        // Nothing to do yet.
    } // end update()

    @Override
    public void draw(Canvas canvas){
        PlayerController player = PlayerController.getInstance();
        Enemy enemy = LevelManager.getInstance().getCurrentEnemy();

        if(canvas != null) {
            score.draw(canvas);
            hpUI.drawHealthBar(canvas, player.getHP(), player.getMaxHP());
            if(enemy != null) {
                enemyHpUI.drawHealthBar(canvas, enemy.getHP(), enemy.getMaxHP());
            }
        }
    } // end draw()
} // end GameHudView class
