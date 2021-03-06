package com.helsanf.jetpacksubmision.utils

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

object IdlingResource {
    private const val RESOURCE = "GLOBAL"
    private val espressoTestIdlingResource = CountingIdlingResource(RESOURCE)

    internal val espressoIdlingResource: IdlingResource
        get() = espressoTestIdlingResource

    internal fun increment() {
        espressoTestIdlingResource.increment()
    }

    internal fun decrement() {
        espressoTestIdlingResource.decrement()
    }
}