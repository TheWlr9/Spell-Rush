package com.spellrush;

import com.spellrush.application.ScoreEntryTests;
import com.spellrush.audio.AudioManagerTest;
import com.spellrush.business.FingerPathTests;
import com.spellrush.business.LeaderboardControllerTests;
import com.spellrush.business.LevelManager;
import com.spellrush.business.LevelManagerTests;
import com.spellrush.business.PlayerControllerTests;
import com.spellrush.business.ShapeRecognitionTests;
import com.spellrush.objects.Enemy;
import com.spellrush.objects.EnemyTests;
import com.spellrush.objects.GameObjectTests;
import com.spellrush.objects.HealthObjectTests;
import com.spellrush.objects.attacks.AttackFactoryTests;
import com.spellrush.objects.attacks.AttackObjectTests;
import com.spellrush.objects.attacks.GameBoardTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        EnemyTests.class,
        LevelManagerTests.class,
        ScoreEntryTests.class,
        LeaderboardControllerTests.class,
        PlayerControllerTests.class,
        GameObjectTests.class,
        FingerPathTests.class,
        AudioManagerTest.class,
        HealthObjectTests.class,
        AttackFactoryTests.class,
        AttackObjectTests.class,
        GameBoardTests.class,
        ShapeRecognitionTests.class
})
public class AllTests {

}
