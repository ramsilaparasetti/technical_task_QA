package com.test.news.Helpers

import android.content.Context
import android.util.Log
import androidx.test.espresso.AmbiguousViewMatcherException
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import java.lang.Thread.sleep


object HelperMatchers {

    private const val defaultWait = 5_000L
    private const val WIFI_ENABLE = "svc wifi enable"
    private const val WIFI_DISABLE = "svc wifi disable"
    private const val DATA_ENABLE = "svc data enable"
    private const val DATA_DISABLE = "svc data disable"
    val uiDevice: UiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    private val context: Context
        get() = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext

    fun waitFor(id: Int, timeout: Long = defaultWait): Boolean {
        val resourceName = context.resources.getResourceName(id)
        return uiDevice.findObject(UiSelector().resourceId(resourceName))
            .waitForExists(timeout)
    }

    fun waitFor(text: String, timeout: Long = defaultWait): Boolean {
        return uiDevice.findObject(UiSelector().text(text))
            .waitForExists(timeout)
    }

    fun assertDisplayed(viewId: Int) {
        waitFor(viewId)
        checkViewExistWithId(viewId)
    }

    fun assertNotDisplayed(viewId: Int) {
        sleep(defaultWait)
        onView(withId(viewId)).check(matches(not(isDisplayed())))
    }

    fun assertDisplayed(text: String) {
        waitFor(text)
        onView(withText(text)).check(matches(isDisplayed()))
    }

    fun assertDisplayed(viewId: Int, text: String) {
        waitFor(viewId)
        onView(withId(viewId)).check(matches(withText(text)))
    }

    fun assertErrorDisplayed(viewId: Int, text: String) {
        waitFor(viewId)
        onView(withId(viewId)).check(matches(hasErrorText(text)))
    }

    private fun checkViewExistWithId(viewId: Int) {
        try {
            onView(withId(viewId)).check(matches(isDisplayed()))
        }
        catch (multipleViewsError: AmbiguousViewMatcherException) {
            assertTrue("Multiple news widgets not displayed on news feed", true)
            onView(firstViewOf(allOf(withId(viewId)))).check(matches(isDisplayed()))
        }
    }

//  Below function created to perform click action on Recycler View how ever it is running into build errors as it requires adding a new dependency hence commented
    fun clickOnImageView(position: Int, viewId: Int) {
//        onView(withId(viewId))
//            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(position,click()))
    }

    private fun <T> firstViewOf(matcher: Matcher<T>): Matcher<T> {
        return object : BaseMatcher<T>() {
            private var isFirst = true
            override fun matches(item: Any): Boolean {
                if (isFirst && matcher.matches(item)) {
                    isFirst = false
                    return true
                }
                return false
            }

            override fun describeTo(description: Description) {
                description.appendText("should return first matching item")
            }
        }
    }

    fun toggleDeviceInternet(internetON: Boolean = true){
        if(internetON) {
            InstrumentationRegistry.getInstrumentation().uiAutomation
                .executeShellCommand(WIFI_ENABLE)
            InstrumentationRegistry.getInstrumentation().uiAutomation
                .executeShellCommand(DATA_ENABLE)
        }
        else {
            InstrumentationRegistry.getInstrumentation().uiAutomation
                .executeShellCommand(WIFI_DISABLE)
            InstrumentationRegistry.getInstrumentation().uiAutomation
                .executeShellCommand(DATA_DISABLE)
        }
    }
}
