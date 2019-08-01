package com.spellrush;

import android.app.Application;

import com.spellrush.presentation.CombatSysTest;
import com.spellrush.presentation.GameOverTest;
import com.spellrush.presentation.LeaderboardActivityTest;
import com.spellrush.presentation.MainMenuTest;
import com.spellrush.presentation.SettingsActivityTest;
import com.spellrush.presentation.UI.FingerPathLayerTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LeaderboardActivityTest.class,
        MainMenuTest.class,
        FingerPathLayerTest.class,
        GameOverTest.class,
        CombatSysTest.class,
        SettingsActivityTest.class
})

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class AllAcceptanceTests extends Application {
    public AllAcceptanceTests() {
        super();
    }
}
