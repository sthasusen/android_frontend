package com.sushil.onlinefoodorder

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
class AddFoods {

    @get:Rule
    val main = ActivityScenarioRule(AddFood::class.java)
    @Test
    fun add(){


        Espresso.onView(ViewMatchers.withId(R.id.name))
                .perform(ViewActions.typeText("soasdasdnam"))
        Thread.sleep(1000)
        Espresso.closeSoftKeyboard()
        Espresso.onView(ViewMatchers.withId(R.id.description))
                .perform(ViewActions.typeText("soasdasdnam"))
        Espresso.closeSoftKeyboard()
        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withId(R.id.rating))
                .perform(ViewActions.typeText("5"))
        Espresso.closeSoftKeyboard()
        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withId(R.id.price))
                .perform(ViewActions.typeText("500"))

        Espresso.closeSoftKeyboard()
        Thread.sleep(2222)
        Espresso.onView(ViewMatchers.withId(R.id.submit))
                .perform(click())
//  Espresso.
//        onView(ViewMatchers.withId(R.id.profileview))
//                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//

    }
}