package com.spellrush;

import com.spellrush.business.GameLevelIT;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        GameLevelIT.class,
})
public class AllIntegrationTests {
}
