package com.bn.weatherforecastapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.bn.weatherforecastapp.weather.ui.activities.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed

@RunWith(AndroidJUnit4::class)
class SearchTest{
    @JvmField
    @Rule
    var mActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun test_Search_Sequence()
    {
        onView(withId(R.id.search_iv)).perform(click())

        onView(withId(R.id.SearcheditText))
            .perform(typeText("mansura"))

        onView(withId(R.id.iv_subbmit_search))
            .perform(click())

        onView(withId(R.id.toolbar_title))
            .check(matches(isDisplayed()))

    }


}