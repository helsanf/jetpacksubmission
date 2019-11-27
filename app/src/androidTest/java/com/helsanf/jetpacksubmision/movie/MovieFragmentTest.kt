package com.helsanf.jetpacksubmision.movie

import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.helsanf.jetpacksubmision.R
import com.helsanf.jetpacksubmision.testing.SingleFragmentActivity
import com.helsanf.jetpacksubmision.utils.IdlingResource
import com.helsanf.jetpacksubmision.utils.RecyclerViewItemCountAssertion
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieFragmentTest{
    @Rule
    @JvmField
    val activityRule : ActivityTestRule<SingleFragmentActivity> = ActivityTestRule(SingleFragmentActivity::class.java)
    private val movieFragment = MovieFragment()


    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(IdlingResource.espressoIdlingResource)
        activityRule.activity.setFragment(movieFragment)
    }
    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(IdlingResource.espressoIdlingResource)
    }
    @Test
    fun loadMovie() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_movie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_movie)).check(RecyclerViewItemCountAssertion(20))
    }
}