package com.sushil.onlinefoodorder



import UserRepo
import android.view.View
import androidx.recyclerview.widget.RecyclerView

import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.espresso.contrib.RecyclerViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import com.kiran.student.api.ServiceBuilder
import com.xrest.finalassignment.Adapter.FoodAdapter
import com.xrest.finalassignment.Class.Person

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.regex.Pattern.matches


@LargeTest
@RunWith(JUnit4::class)
class RVTesting {



    @get:Rule
    val main = ActivityScenarioRule(MainActivity::class.java)
    @Test
    fun testView(){

        runBlocking {
            var userRepository = UserRepo()
            ServiceBuilder.token = "Bearer " + userRepository.Login("123", "123").token
            ServiceBuilder.user = userRepository.Login("123", "123").data
        }

        onView(withId(R.id.rv)).perform(
                actionOnItemAtPosition<FoodAdapter.FoodHolder>(0,clickOnViewChild(R.id.book))
        )
        Thread.sleep(2000)
        onView(withId(R.id.search))
                .check(matches(isDisplayed()))
    }





    private fun clickOnViewChild(viewId: Int) = object : ViewAction {
        override fun getConstraints() = null

        override fun getDescription() = "Click on a child view with specified id."

        override fun perform(uiController: UiController, view: View) = ViewActions.click()
                .perform(uiController, view.findViewById<View>(viewId))
    }

}


