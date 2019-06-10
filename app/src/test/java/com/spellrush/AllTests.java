package com.spellrush;

import com.spellrush.business.*;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PlayerControllerTests.class,
        GameObjectTests.class,
        FingerPathTests.class
})

public class AllTests
{

}
