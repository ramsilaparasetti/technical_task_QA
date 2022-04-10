package com.test.news

import android.content.Context
import android.view.MenuItem
import android.view.View
import androidx.annotation.IdRes
import androidx.test.espresso.AmbiguousViewMatcherException
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf


object HelperMatchers {
    fun <T> atPosition(position: Int, matcher: Matcher<T>): Matcher<T> {
        return object : BaseMatcher<T>() {
            var matchingPosition = 0
            override fun matches(item: Any): Boolean {
                if (!matcher.matches(item)) {
                    return false
                }
                return if (matchingPosition++ == position) {
                    true
                } else false
            }

            override fun describeTo(description: Description) {
                description.appendText("should return matching item at position $position")
            }
        }
    }

    fun <T> firstViewOf(matcher: Matcher<T>): Matcher<T> {
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

//    fun menuIdMatcher(@IdRes id: Int): Matcher<MenuItem> {
//        return object : BoundedMatcher<MenuItem?, MenuItem>(MenuItem::class.java) {
//            override fun matchesSafely(item: MenuItem): Boolean {
//                return item.itemId == id
//            }
//
//            override fun describeTo(description: Description) {
//                description.appendText("should return menu item with id $id")
//            }
//        }
//    }

    fun withParentId(@IdRes parentId: Int): Matcher<View> {
        return ViewMatchers.withParent(ViewMatchers.withId(parentId))
    }
    val uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    val context: Context
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

    const val defaultWait = 7_000L
    fun assertDisplayed(viewId: Int) {
        waitFor(viewId)
        viewId.resourceMatcher().assertAny(isDisplayed())

//        try {
//            onView(withId(viewId)).check(matches(isDisplayed()))
//        }
//        catch (multipleViewsError: AmbiguousViewMatcherException) {
//            onView(firstViewOf(allOf(withId(viewId)))).check(matches(isDisplayed()))
//        }
    }

//    fun assertDisplayed12(viewId: Int) {
//        waitFor(viewId)
//        viewId.assertAny1(ViewMatchers.isDisplayed())
//    }

    fun assertDisplayed(text: String) {
        waitFor(text)
        withText(text).assertAny(isDisplayed())
    }

    fun assertDisplayed(viewId: Int, text: String) {
        waitFor(viewId)
        viewId.resourceMatcher().assertAny(withText(text))
        //viewId.assertAny1(ViewMatchers.withText(text))
    }

    fun assertErrorDisplayed(viewId: Int, text: String) {
        waitFor(viewId)
        viewId.resourceMatcher().assertAny(hasErrorText(text))
        //viewId.assertAny1(ViewMatchers.hasErrorText(text))
    }
}
