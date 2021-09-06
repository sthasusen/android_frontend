package com.sushil.onlinefoodorder

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
class LoginTest {
    @get:Rule
    val testRule = ActivityScenarioRule(LoginActivity::class.java)
    @Test
    fun checkArithmeticUI() {

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
    }
}