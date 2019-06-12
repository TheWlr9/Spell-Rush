package com.spellrush;

import com.spellrush.application.EnemyTest;
import com.spellrush.application.ScoreEntryTest;
import com.spellrush.business.FingerPathTests;
import com.spellrush.business.GameObjectTests;
import com.spellrush.business.LeaderboardControllerTest;
import com.spellrush.business.PlayerControllerTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ScoreEntryTest.class,
        LeaderboardControllerTest.class,
        PlayerControllerTests.class,
        EnemyTest.class,
        PlayerControllerTests.class,
        GameObjectTests.class,
        FingerPathTests.class

})
public class AllTests {

}
