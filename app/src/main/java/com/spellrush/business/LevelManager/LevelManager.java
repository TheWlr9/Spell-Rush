package com.spellrush.business.LevelManager;

import android.content.res.Resources;
import android.graphics.Canvas;

import com.spellrush.business.GameView;
import com.spellrush.business.IEnemyAI;
import com.spellrush.business.PlayerController;
import com.spellrush.objects.Enemy;
import com.spellrush.objects.GameObject;
import com.spellrush.objects.NullEnemyAI;
import com.spellrush.objects.attacks.GameBoard;

public class LevelManager extends GameObject {
    // Follow Singleton Design Pattern
    private static final LevelManager instance = new LevelManager();

    private static final int LVL_COMPLETE_BONUS = 1000;

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

        currLevel = Level.LEVEL_1;
        currEnemy = Enemy.getInstance();
        currEnemy.resetHP();
        currEnemy.setAI(currLevel.getEnemy());
        this.gameBoard = new GameBoard(1, Enemy.Y_POSITION, deviceHeight - (Enemy.Y_POSITION * 2), MAX_BULLETS);
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

        if(currEnemy.getAI() instanceof NullEnemyAI) {
            //The level has been beaten
            PlayerController.getInstance().addScore(LVL_COMPLETE_BONUS);

            initNextLevel();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if(currEnemy != null) {
            currEnemy.draw(canvas);
        }
        if(gameBoard != null) {
            gameBoard.draw(canvas);
        }
    }

    public String getCurrentLevel(){
        return this.currLevel.getName();
    }

    public void setCurrentEnemyAI(IEnemyAI brain){
        currEnemy.setAI(brain);
    }

    // Call update function for enemy if it exists
    private void updateEnemy(){
            currEnemy.update();
     }

    private void initNextLevel(){
        if(currLevel.getNext() != null) {
            currLevel = currLevel.getNext();
            gameBoard.clear();
            System.out.println("Current Level: " + currLevel.getName());

            if(GameView.getInstance() != null){
                GameView.getInstance().triggerNextLevel(currLevel.getScore());
            }

            currEnemy.resetHP();
            setCurrentEnemyAI(currLevel.getEnemy());
        }
        else if(GameView.getInstance() != null) {
            GameView.getInstance().triggerGameOver();
        }
    }

    // Called when restarting the game
    public void reset(){
        init();
    }
}
