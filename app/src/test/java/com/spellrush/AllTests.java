package com.spellrush;

import com.spellrush.business.*;
import com.spellrush.objects.ScoreEntryTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PlayerControllerTests.class,
        GameObjectTests.class,
        FingerPathTests.class,
        ScoreEntryTest.class,
        LeaderboardControllerTest.class,
        PlayerControllerTests.class
})
public class AllTests {

}
