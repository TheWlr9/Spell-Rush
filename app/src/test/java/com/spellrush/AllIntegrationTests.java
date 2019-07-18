package com.spellrush;

import com.spellrush.business.GameLevelIT;
import com.spellrush.business.LeaderboardIT;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        GameLevelIT.class,
        LeaderboardIT.class
})
public class AllIntegrationTests {
}
