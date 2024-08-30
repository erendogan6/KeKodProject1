package com.erendogan6.kekodproject1

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isNotChecked
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testEgoSwitchInitialCondition() {
        // Check Ego Switch is displayed and checked
        onView(withId(R.id.switchEgo)).check(matches(isDisplayed()))
        onView(withId(R.id.switchEgo)).check(matches(isChecked()))

        // Check other switches are disabled
        onView(withId(R.id.switch1)).check(matches(isNotEnabled()))
        onView(withId(R.id.switch2)).check(matches(isNotEnabled()))
        onView(withId(R.id.switch3)).check(matches(isNotEnabled()))
        onView(withId(R.id.switch4)).check(matches(isNotEnabled()))
        onView(withId(R.id.switch5)).check(matches(isNotEnabled()))
    }

    @Test
    fun testBottomNavigationVisibilityOnEgoSwitchToggle() {
        // Toggle Ego switch to OFF
        onView(withId(R.id.switchEgo)).perform(click())

        // Check BottomNavigationView is visible
        onView(withId(R.id.bottomNavigationView)).check(matches(isDisplayed()))

        // Toggle Ego switch to ON
        onView(withId(R.id.switchEgo)).perform(click())

        // Check BottomNavigationView is gone
        onView(withId(R.id.bottomNavigationView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
    }

    @Test
    fun testAddingIconsToBottomNavigationView() {
        // Toggle Ego switch to OFF
        onView(withId(R.id.switchEgo)).perform(click())

        // Turn on switch 1 and check if the icon is added to BottomNavigationView
        onView(withId(R.id.switch1)).perform(click())
        onView(allOf(withId(R.id.switch1), withText("Happy"))).perform(click())

        // Turn on switch 2 and check if the icon is added to BottomNavigationView
        onView(withId(R.id.switch2)).perform(click())
        onView(allOf(withId(R.id.switch2), withText("Money"))).perform(click())

        // Turn on switch 3 and check if the icon is added to BottomNavigationView
        onView(withId(R.id.switch3)).perform(click())
        onView(allOf(withId(R.id.switch3), withText("Peace"))).perform(click())
    }

    @Test
    fun testRemovingIconsFromBottomNavigationView() {
        // Toggle Ego switch to OFF
        onView(withId(R.id.switchEgo)).perform(click())

        // Turn on switches
        onView(withId(R.id.switch1)).perform(click())
        onView(withId(R.id.switch2)).perform(click())

        // Turn off switch 1
        onView(withId(R.id.switch1)).perform(click())

        // Check that switch1 is unchecked and still exists
        onView(withId(R.id.switch1)).check(matches(isNotChecked()))

        // Check that the "Happy" text does not exist in the BottomNavigationView
        onView(allOf(withText("Happy"), isDescendantOfA(withId(R.id.bottomNavigationView))))
            .check(doesNotExist())
    }

    @Test
    fun testNavigationToDetailScreens() {
        // Step 1: Use Espresso to navigate to the detail screen

        // Toggle Ego switch to OFF using Espresso
        onView(withId(R.id.switchEgo)).perform(click())

        // Turn on switch 1 using Espresso
        onView(withId(R.id.switch1)).perform(click())

        // Click on "Happy" navigation item within BottomNavigationView using Espresso
        onView(
            allOf(
                withText("Happy"),
                isDescendantOfA(withId(R.id.bottomNavigationView)),
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
            ),
        ).perform(click())

        // Step 2: Wait for Compose content and use Compose testing utilities

        // Wait until the Compose content is available
        composeTestRule.waitForIdle()

        // Check if "Welcome To Happy Fragment" is displayed using Compose testing APIs
        composeTestRule.onNodeWithText("Welcome To Happy Fragment").assertExists()
    }

    @Before
    fun disableAnimations() {
        val disableCommand =
            arrayOf(
                "settings put global window_animation_scale 0",
                "settings put global transition_animation_scale 0",
                "settings put global animator_duration_scale 0",
            )
        for (command in disableCommand) {
            try {
                Runtime.getRuntime().exec(command)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}
