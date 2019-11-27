package com.helsanf.jetpacksubmision.detail

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.helsanf.jetpacksubmision.R
import com.helsanf.jetpacksubmision.model.Movie
import com.helsanf.jetpacksubmision.utils.FakeDataDummy
import com.helsanf.jetpacksubmision.utils.IdlingResource
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailActivityTest{
    val dummyMovie : Movie = FakeDataDummy().generateMovie().get(0)
    @Rule
    @JvmField
    var activityRule: ActivityTestRule<DetailActivity> =
        object : ActivityTestRule<DetailActivity>(DetailActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext()
                val result = Intent(targetContext, DetailActivity::class.java)
                result.putExtra("movie_id", dummyMovie.movieId)
                return result
            }
        }

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(IdlingResource.espressoIdlingResource)
    }
    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(IdlingResource.espressoIdlingResource)
    }

    @Test
    fun loadDetailMovie(){
        Espresso.onView(ViewMatchers.withId(R.id.tvTitle)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_rating)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        assertNotNull(dummyMovie)

    }
}