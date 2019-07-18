package com.spellrush.presentation;


import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import com.spellrush.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TutorialTest {

    @Rule
    public IntentsTestRule<TutorialActivity> TutorialActivityTestRule = new IntentsTestRule(TutorialActivity.class);

    @NonNull
    public static Matcher<View> inPage(final int page) {

        return new BoundedMatcher<View, ViewPager>(ViewPager.class) {

            @Override
            public void describeTo(final Description description) {
                description.appendText("in page: " + page);
            }

            @Override
            public boolean matchesSafely(final ViewPager viewPager) {
                return viewPager.getCurrentItem() == page;
            }
        };
    }

    @Test
    public void swipeThroughTutPages() {
        int numPages = 3;
        for (int i = 0; i < numPages; i++) {
            // Check the ViewPager is in page i
            onView(withId(R.id.tutorial)).check(matches(isDisplayed()));

            if (i < numPages - 1) {
                // perform swipe if not on last page
                onView(withId(R.id.tutorial)).perform(swipeLeft());
                SystemClock.sleep(800);
            }
        }
    }
}
