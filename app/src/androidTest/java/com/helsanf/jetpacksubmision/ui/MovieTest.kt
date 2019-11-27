package com.helsanf.jetpacksubmision.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.helsanf.jetpacksubmision.MainActivity
import com.helsanf.jetpacksubmision.R
import com.helsanf.jetpacksubmision.utils.IdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieTest {
    @Rule
    @JvmField
    val activityRule : ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(IdlingResource.espressoIdlingResource)

    }
    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(IdlingResource.espressoIdlingResource)
    }

    @Test
    fun toDetailActivity(){
        Espresso.onView(ViewMatchers.withId(R.id.rv_movie)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Espresso.onView(ViewMatchers.withId(R.id.tvTitle)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }
}