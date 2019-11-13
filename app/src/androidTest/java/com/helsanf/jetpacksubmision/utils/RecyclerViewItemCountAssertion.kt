package com.helsanf.jetpacksubmision.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import junit.framework.Assert.assertNotNull
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import org.hamcrest.core.Is.`is`


class RecyclerViewItemCountAssertion : ViewAssertion{
     var expectedCount : Int? = null

    constructor(expectedCount : Int){
        this.expectedCount = expectedCount
    }

    override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
        val recyclerView : RecyclerView = view as RecyclerView
        val adapter = recyclerView.adapter
        assertNotNull(adapter)
        assertThat(adapter!!.itemCount, `is`(expectedCount))

    }
}