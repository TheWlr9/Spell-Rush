package com.spellrush.presentation;


import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import com.spellrush.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainMenuTest {

    @Rule
    public IntentsTestRule<HomeActivity> homeActivityTestRule = new IntentsTestRule(HomeActivity.class);

    @Test
    public void homeActivityTest(){
        onView(ViewMatchers.withId(R.id.textTitle)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.chadName)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.mikeName)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.willName)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.nathanName)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.earlName)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.trevorName)).check(matches(isDisplayed()));
    }

    @Test
    public void startButtonTest() {
        onView(ViewMatchers.withId(R.id.startBtn)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.startBtn)).perform(click());
        intended(hasComponent(GameActivity.class.getName()));
    }

    @Test
    public void leaderboardButtonTest() {
        onView(ViewMatchers.withId(R.id.leaderBoardBtn)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.leaderBoardBtn)).perform(click());
        intended(hasComponent(LeaderboardActivity.class.getName()));
    }
}
