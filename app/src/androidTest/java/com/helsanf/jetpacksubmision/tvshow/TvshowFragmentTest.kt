package com.helsanf.jetpacksubmision.tvshow

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

class TvshowFragmentTest{
    @Rule
    @JvmField
    val activityRule : ActivityTestRule<SingleFragmentActivity> = ActivityTestRule(
        SingleFragmentActivity::class.java)
    private val tvShowFragment = TvshowFragment()

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(IdlingResource.espressoIdlingResource)
        activityRule.activity.setFragment(tvShowFragment)
    }
    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(IdlingResource.espressoIdlingResource)
    }
    @Test
    fun loadTvShow() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_tvshow))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_tvshow)).check(RecyclerViewItemCountAssertion(20))
    }
}