package com.spellrush.business.LevelManager;

import com.spellrush.business.IEnemyAI;
import com.spellrush.objects.BasicEnemyAI;
import com.spellrush.objects.MediumEnemyAI;

public enum Level {
    LEVEL_1("LEVEL 1", 100, new BasicEnemyAI()),
    LEVEL_2("LEVEL 2", 200, new BasicEnemyAI()),
    LEVEL_3("LEVEL 3", 300, new MediumEnemyAI());

    private String name;
    private int score;
    private IEnemyAI enemy;

    Level(final String name, final int score, IEnemyAI enemy) {
        this.name = name;
        this.score = score;
        this.enemy = enemy;
    }

    public Level getNext() {
        return this.ordinal() < Level.values().length - 1 ? Level.values()[this.ordinal() + 1] : null;
    }

    public String getName() {
        return this.name;
    }

    public IEnemyAI getEnemy() {
        return this.enemy;
    }

    public int getScore() {
        return this.score;
    }
}