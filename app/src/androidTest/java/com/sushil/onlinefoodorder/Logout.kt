package com.sushil.onlinefoodorder

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test


class Logout {
    @get:Rule
    val testRule = ActivityScenarioRule(LoginActivity::class.java)
    @Test
    fun logout() {

        Espresso.onView(ViewMatchers.withId(R.id.et_email))
                .perform(ViewActions.typeText("sonam"))
        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withId(R.id.et_password))
                .perform(ViewActions.typeText("sonam"))
        Thread.sleep(1000)
        Espresso.closeSoftKeyboard()
        Espresso.onView(ViewMatchers.withId(R.id.bt_login))
                .perform(ViewActions.click())

        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(R.id.aboutview))
                .perform(ViewActions.click())
    }
}