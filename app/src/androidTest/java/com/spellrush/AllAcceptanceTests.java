package com.spellrush;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.spellrush.presentation.GameOverTest;
import com.spellrush.presentation.MainMenuTest;
import com.spellrush.presentation.UI.FingerPathLayerTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MainMenuTest.class,
        FingerPathLayerTest.class,
        GameOverTest.class,
})

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class AllAcceptanceTests extends ApplicationTestCase<Application> {
    public AllAcceptanceTests() {
        super(Application.class);
    }
}
