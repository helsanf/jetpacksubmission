package com.helsanf.jetpacksubmision.utils

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

class IdlingResource {
    companion object {
        private const val RESOURCE = "GLOBAL"

    }

    private val espressoTestIdlingResource: CountingIdlingResource =
        CountingIdlingResource(RESOURCE)

    fun increment() {
        espressoTestIdlingResource.increment()
    }

    fun decrement() {
        espressoTestIdlingResource.decrement()
    }

    fun getEspressoIdlingResource(): IdlingResource {
        return espressoTestIdlingResource
    }
}