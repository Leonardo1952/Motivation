package com.example.motivation.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.motivation.R
import junit.framework.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(UserActivity::class.java)

    @Test
    fun test_user_activity_is_displayed() {
        onView(withId(R.id.edit_name)).check(matches(isDisplayed()))
        onView(withId(R.id.button_save)).check(matches(isDisplayed()))
    }

    @Test
    fun test_finish_activity_when_user_name_is_valid() {
        val scenario = ActivityScenario.launch(UserActivity::class.java)

        onView(withId(R.id.edit_name)).perform(
            ViewActions.typeText("User"),
            ViewActions.closeSoftKeyboard()
        )
        onView(withId(R.id.button_save)).perform(ViewActions.click())

        scenario.onActivity { activity ->
            TestCase.assertTrue(activity.isFinishing)
        }
    }

    @Test
    fun test_activity_not_finishing_when_user_name_is_invalid() {
        val scenario = ActivityScenario.launch(UserActivity::class.java)

        onView(withId(R.id.edit_name)).perform(
            ViewActions.typeText(""),
            ViewActions.closeSoftKeyboard()
        )
        onView(withId(R.id.button_save)).perform(ViewActions.click())

        scenario.onActivity { activity ->
            TestCase.assertFalse(activity.isFinishing)
        }
    }
}
