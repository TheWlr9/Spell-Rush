package com.spellrush.presentation;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.spellrush.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LeaderboardActivityTest {

    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void leaderboardActivityTest() {
        ViewInteraction button = onView(
                allOf(withId(R.id.leaderBoardBtn),
                        childAtPosition(
                                allOf(withId(R.id.relativeLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                3),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.leaderBoardBtn), withText("Leader Board"),
                        childAtPosition(
                                allOf(withId(R.id.relativeLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                3),
                        isDisplayed()));
        button2.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.leaderboard), withText("Leaderboard"),
                        childAtPosition(
                                allOf(withId(R.id.leaderboard_layou),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Leaderboard")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.leaderboard_header_name), withText("Player Name"),
                        childAtPosition(
                                allOf(withId(R.id.table_row1),
                                        childAtPosition(
                                                withId(R.id.leaderboard_table),
                                                0)),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("Player Name")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.leaderboard_header_score), withText("Score"),
                        childAtPosition(
                                allOf(withId(R.id.table_row1),
                                        childAtPosition(
                                                withId(R.id.leaderboard_table),
                                                0)),
                                1),
                        isDisplayed()));
        textView3.check(matches(withText("Score")));

        ViewInteraction button3 = onView(
                allOf(withId(R.id.ldr_brd_Menu),
                        childAtPosition(
                                allOf(withId(R.id.leaderboard_layou),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                2),
                        isDisplayed()));
        button3.check(matches(isDisplayed()));

        ViewInteraction button4 = onView(
                allOf(withId(R.id.ldr_brd_Menu), withText("Main Menu"),
                        childAtPosition(
                                allOf(withId(R.id.leaderboard_layou),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                2),
                        isDisplayed()));
        button4.perform(click());

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.textTitle), withText("Spell Rush"),
                        childAtPosition(
                                allOf(withId(R.id.relativeLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                0),
                        isDisplayed()));
        textView4.check(matches(withText("Spell Rush")));
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
