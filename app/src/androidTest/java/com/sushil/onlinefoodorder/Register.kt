package com.sushil.onlinefoodorder

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class Register {

    @get:Rule
    val main = ActivityScenarioRule(SignupActivity::class.java)

    @Test
    fun register() {
        Espresso.onView(ViewMatchers.withId(R.id.etFname))
                .perform(ViewActions.typeText("soasdasdnam"))
        Thread.sleep(1000)
        Espresso.closeSoftKeyboard()
        Espresso.onView(ViewMatchers.withId(R.id.Male))
                .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.etLname))
                .perform(ViewActions.typeText("soasdasdnam"))
        Espresso.closeSoftKeyboard()
        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withId(R.id.etAddress))
                .perform(ViewActions.typeText("sonasdasdam"))
        Espresso.closeSoftKeyboard()
        Espresso.onView(ViewMatchers.withId(R.id.etEmail))
                .perform(ViewActions.typeText("sonasdasdam"))
        Espresso.closeSoftKeyboard()
        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withId(R.id.etPhone))
                .perform(ViewActions.typeText("8888888888"))
        Thread.sleep(1000)
        Espresso.closeSoftKeyboard()
        Espresso.onView(ViewMatchers.withId(R.id.etUsername))
                .perform(ViewActions.typeText("456"))
        Espresso.closeSoftKeyboard()
        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withId(R.id.etPassword))
                .perform(ViewActions.typeText("456"))
        Espresso.closeSoftKeyboard()
        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withId(R.id.etCPassword))
                .perform(ViewActions.typeText("456"))
        Espresso.closeSoftKeyboard()
        Thread.sleep(1000)
        Espresso.closeSoftKeyboard()
        Espresso.onView(ViewMatchers.withId(R.id.btnReg))
                .perform(ViewActions.click())
        Thread.sleep(4000)

    }
}
