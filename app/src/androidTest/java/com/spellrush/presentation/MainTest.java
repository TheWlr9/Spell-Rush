package com.spellrush.presentation;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.*;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.spellrush.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainTest {

    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(HomeActivity.class);

    /*
        Assume that the initial activity is HomeActivity, and that all methods
        called in mainTest() will return to the HomeActivity eventually (kind of
        like the heap!
     */
    @Test
    public void mainTest() {
        homeTest();

        ViewInteraction button3 = onView(
                allOf(withId(R.id.leaderBoardBtn), withText("Leader Board"),
                        childAtPosition(
                                allOf(withId(R.id.relativeLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                3),
                        isDisplayed()));
        button3.perform(click());

        leaderBoardTest();

        ViewInteraction relativeLayout = onView(
                allOf(withId(R.id.relativeLayout),
                        isDisplayed()));
        relativeLayout.check(matches(isDisplayed()));

        ViewInteraction button4 = onView(
                allOf(withId(R.id.startBtn), withText("Start"),
                        childAtPosition(
                                allOf(withId(R.id.relativeLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        button4.perform(click());

        gameTest();

        ViewInteraction relativeLayout2 = onView(
                allOf(withId(R.id.relativeLayout),
                        childAtPosition(
                                allOf(withId(android.R.id.content),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                0),
                        isDisplayed()));
        relativeLayout2.check(matches(isDisplayed()));
    }

    /*
        Assume that the activity is set to HomeActivity
     */
    public void homeTest(){
        ViewInteraction textView = onView(
                allOf(withId(R.id.textTitle), withText("Spell Rush"),
                        childAtPosition(
                                allOf(withId(R.id.relativeLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.willName), withText("Will Ritchie"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.relativeLayout),
                                        4),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("Will Ritchie")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.earlName), withText("Earl John Placido"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.relativeLayout),
                                        4),
                                1),
                        isDisplayed()));
        textView3.check(matches(withText("Earl John Placido")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.nathanName), withText("Nathan Carrier"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.relativeLayout),
                                        4),
                                2),
                        isDisplayed()));
        textView4.check(matches(withText("Nathan Carrier")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.chadName), withText("Chad Hillary"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.relativeLayout),
                                        4),
                                3),
                        isDisplayed()));
        textView5.check(matches(withText("Chad Hillary")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.mikeName), withText("Mike Winkler"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.relativeLayout),
                                        4),
                                4),
                        isDisplayed()));
        textView6.check(matches(withText("Mike Winkler")));

        ViewInteraction button = onView(
                allOf(withId(R.id.startBtn),
                        childAtPosition(
                                allOf(withId(R.id.relativeLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.leaderBoardBtn),
                        childAtPosition(
                                allOf(withId(R.id.relativeLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                3),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));
    }

    /*
        Assume that the activity is set to LeaderboardActivity
     */
    public void leaderBoardTest(){
        ViewInteraction textView7 = onView(
                allOf(withId(R.id.leaderboard), withText("Leaderboard"),
                        childAtPosition(
                                allOf(withId(R.id.leaderboard_layou),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                0),
                        isDisplayed()));
        textView7.check(matches(isDisplayed()));

        ViewInteraction tableRow = onView(
                allOf(withId(R.id.table_row1),
                        childAtPosition(
                                allOf(withId(R.id.leaderboard_table),
                                        childAtPosition(
                                                withId(R.id.leaderboard_layou),
                                                1)),
                                0),
                        isDisplayed()));
        tableRow.check(matches(isDisplayed()));

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.leaderboard_header_name), withText("Player Name"),
                        childAtPosition(
                                allOf(withId(R.id.table_row1),
                                        childAtPosition(
                                                withId(R.id.leaderboard_table),
                                                0)),
                                0),
                        isDisplayed()));
        textView8.check(matches(withText("Player Name")));

        ViewInteraction textView9 = onView(
                allOf(withId(R.id.leaderboard_header_score), withText("Score"),
                        childAtPosition(
                                allOf(withId(R.id.table_row1),
                                        childAtPosition(
                                                withId(R.id.leaderboard_table),
                                                0)),
                                1),
                        isDisplayed()));
        textView9.check(matches(withText("Score")));

        ViewInteraction view = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.leaderboard_table),
                                childAtPosition(
                                        withId(R.id.leaderboard_layou),
                                        1)),
                        1),
                        isDisplayed()));
        view.check(matches(isDisplayed()));

        ViewActions.pressBack();
    }

    /*
        Assume that the activity is set to GameActivity
     */
    public void gameTest(){
        ViewInteraction view2 = onView(
                allOf(withId(R.id.surfaceView),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        view2.check(matches(isDisplayed()));

        ViewInteraction button5 = onView(
                allOf(withId(R.id.menuBtn),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout1),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                                1)),
                                0),
                        isDisplayed()));
        button5.check(matches(isDisplayed()));

        ViewInteraction button6 = onView(
                allOf(withId(R.id.menuBtn), withText("QUIT"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout1),
                                        childAtPosition(
                                                withClassName(is("android.widget.FrameLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        button6.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
