package com.spellrush.business;

import android.graphics.Canvas;

import com.spellrush.objects.BasicEnemy;
import com.spellrush.objects.Enemy;
import com.spellrush.objects.GameObject;

enum levels{
    level_1
}

// TODO: flesh this out, make it modular for differant enemies... (see "level manager" story)
public class LevelManager extends GameObject {

    // Follow Singleton Design Pattern
    private static final LevelManager instance = new LevelManager();

    public static final int ENEMY_DEPTH = 50;
    private Enemy currEnemy;
    private levels currLevel;


    private LevelManager(){
        super(ENEMY_DEPTH);
        init();
    }

    // Initialize the Level Manager (Level 0)
    private void init(){
        currLevel = levels.level_1;
        this.setCurrentEnemy(new BasicEnemy(400,400,ENEMY_DEPTH,30));
    }

    public static LevelManager getInstance(){
        return instance;
    }

    public void setCurrentEnemy(Enemy newEnemy){
        if(newEnemy != null) {
            currEnemy = newEnemy;
        }
    }

    public Enemy getCurrentEnemy(){
        return currEnemy;
    }


    @Override
    public void update() {
        if(currEnemy != null) {
            currEnemy.update();
            if (!currEnemy.isAlive()) {
                currEnemy = null;
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if(currEnemy != null) {
            currEnemy.draw(canvas);
        }
    }

    // Called when restarting the game
    public void reset(){
        init();
    }
}
