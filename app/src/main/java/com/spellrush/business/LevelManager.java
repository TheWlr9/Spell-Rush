package com.spellrush.business;

import android.content.res.Resources;
import android.graphics.Canvas;

import com.spellrush.objects.BasicEnemyAI;
import com.spellrush.objects.Enemy;
import com.spellrush.objects.GameObject;
import com.spellrush.objects.MediumEnemyAI;
import com.spellrush.objects.MediumEnemyAI;
import com.spellrush.objects.attacks.GameBoard;

enum Level{
    level_1
}

// TODO: flesh this out, make it modular for differant enemies... (see "level manager" story)
public class LevelManager extends GameObject {
    // Follow Singleton Design Pattern
    private static final LevelManager instance = new LevelManager();

    public static final int ENEMY_DEPTH = 50;
    public static final int MAX_BULLETS = 10; // Max bullets at a time for each player

    private GameBoard gameBoard;
    private Enemy currEnemy;
    private Level currLevel;

    private LevelManager(){
        super(ENEMY_DEPTH);
        init();
    }

    // Initialize the Level Manager (Level 0)
    private void init(){
        int deviceHeight;
        try {
            deviceHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        } catch (RuntimeException e) {
            // Catch if testing, and this is not mocked
            deviceHeight = 0;
        }

        currLevel = Level.level_1;
        this.gameBoard = new GameBoard(1, 200, deviceHeight - 200, MAX_BULLETS);
        currEnemy=Enemy.getInstance();
        currEnemy.setAI(new MediumEnemyAI());
    }

    public static LevelManager getInstance(){
        return instance;
    }

    /* Used to add attacks to the board in  AttackFactory */
    public GameBoard getGameBoard(){ return gameBoard; }

    @Override
    public void update() {
        if(gameBoard != null) {
            gameBoard.update();
        }
        updateEnemy();
    }

    @Override
    public void draw(Canvas canvas) {
        if(gameBoard != null) {
            gameBoard.draw(canvas);
        }
        if(currEnemy != null) {
            currEnemy.draw(canvas);
        }
    }

    public void setCurrentEnemyAI(IEnemyAI brain){
        currEnemy.setAI(brain);
    }

    // Call update function for enemy if it exists
    private void updateEnemy(){
            currEnemy.update();
     }

    // Called when restarting the game
    public void reset(){
        init();
    }
}
