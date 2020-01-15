package com.project.segunfrancis.roomwordsamplekotlin

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by SegunFrancis
 */

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    // Creates the activity
    @get: Rule
    val activityTest = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_isActivityInView() {

        onView(withId(R.id.mainActivity)).check(matches(isDisplayed()))
    }

    @Test
    fun test_navigateToNextFragment() {

        onView(withId(R.id.fab)).check(matches(isDisplayed())).perform(click())
    }

    @Test
    fun test_isSaveButtonDisplayed() {

        onView(withId(R.id.fab)).check(matches(isDisplayed())).perform(click())
        onView(withId(R.id.button_save)).check(matches(isDisplayed()))
    }

    @Test
    fun test_navigateBackUsingBackButton() {

        onView(withId(R.id.fab)).check(matches(isDisplayed())).perform(click())
        pressBack()
        onView(withId(R.id.mainActivity)).check(matches(isDisplayed()))
    }
}