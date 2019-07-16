package com.spellrush.presentation;


import androidx.test.espresso.Espresso;
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
public class GameOverTest {

    @Rule
    public IntentsTestRule<GameOverActivity> goActivityTestRule = new IntentsTestRule(GameOverActivity.class);

    @Test
    public void gameoverDisplayTest(){
        onView(ViewMatchers.withId(R.id.GameOver)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.FinalScore)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.score)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.name)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.Submit_score)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.PlayAgain)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.leaderBoardBtn)).check(matches(isDisplayed()));
    }

    @Test
    public void playAgainButtonTest() {
        onView(ViewMatchers.withId(R.id.PlayAgain)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.PlayAgain)).perform(click());
        intended(hasComponent(GameActivity.class.getName()));
    }

    @Test
    public void leaderboardButtonTest() {
        onView(ViewMatchers.withId(R.id.leaderBoardBtn)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.leaderBoardBtn)).perform(click());
        intended(hasComponent(LeaderboardActivity.class.getName()));
    }

    @Test
    public void submitScoreButtonTest() {
        onView(ViewMatchers.withId(R.id.Submit_score)).check(matches(isDisplayed()));
        // Click is tested in LeaderboardTest
    }

    @Test
    public void goBackToMenuTest() {
        Espresso.pressBack();
        // Can't test this because pressBack will always exit the app when on the first activity launched!
        // (And we need to start at this activity to avoid non-deterministic access of it!)
        // intended(hasComponent(HomeActivity.class.getName()));
    }
}
