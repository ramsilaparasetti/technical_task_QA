package com.test.news

//package com.schibsted.spain.barista.internal.util

import androidx.test.InstrumentationRegistry
import android.view.View
import androidx.test.espresso.AmbiguousViewMatcherException
//import androidx.test.espresso.AmbiguousViewMatcherException
//import androidx.test.espresso.NoMatchingViewException
import org.hamcrest.Matcher
//import androidx.test.espresso.AmbiguousViewMatcherException
import androidx.test.espresso.Espresso.onView
//import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.test.news.HelperMatchers.firstViewOf
import org.hamcrest.Matchers.allOf

//import android.view.View
//import com.schibsted.spain.barista.internal.failurehandler.SpyFailureHandler
//import com.schibsted.spain.barista.internal.failurehandler.description
//import com.schibsted.spain.barista.internal.matcher.HelperMatchers.firstViewOf
//import org.hamcrest.Matcher
//import org.hamcrest.Matchers.allOf
//import java.util.regex.Pattern.matches

class BaristaResourceTypeException(message: String) : RuntimeException(message)

enum class ResourceType {
    ID, STRING
}

val Int.resourceType: ResourceType
    get() {
        val resourceTypeName = InstrumentationRegistry.getTargetContext().resources.getResourceTypeName(this)
        return when (resourceTypeName) {
            "id" -> ResourceType.ID
            "string" -> ResourceType.STRING
            else -> throw BaristaResourceTypeException("The id argument must be R.id.* or R.string.*, but was $resourceTypeName")
        }
    }

fun Int.resourceMatcher(): Matcher<View> = when (resourceType) {
    ResourceType.ID -> withId(this)
    ResourceType.STRING -> withText(this)
}

fun Matcher<View>.assertAny(condition: Matcher<View>) {
    assertAnyView(viewMatcher = this, condition = condition)
}



fun assertAnyView(viewMatcher: Matcher<View>, condition: Matcher<View>) {

    //val spyFailureHandler = SpyFailureHandler()
    try {
        tryToAssert(viewMatcher, condition)

    }
    catch (multipleViewsError: AmbiguousViewMatcherException) {
        //try {
        tryToAssertFirstView(viewMatcher, condition)
        //tryToAssertFirstView1(viewMatcher)
    }
//                catch (noneMatchedError: Throwable) {
////            spyFailureHandler.resendFirstError(
////                    "None of the views matching (${viewMatcher.description()}) did match the condition (${condition.description()})")
//       }
//    }
    //    catch (singleViewNotFoundError: NoMatchingViewException) {
//        spyFailureHandler.resendFirstError("No view matching (${viewMatcher.description()}) was found")
//    } catch (singleViewMatchError: Throwable) {
//        spyFailureHandler.resendFirstError("View (${viewMatcher.description()}) didn't match condition (${condition.description()})")
//    }
}
fun tryToAssertFirstView(viewMatcher: Matcher<View>, condition: Matcher<View>) {
    onView(firstViewOf(allOf(viewMatcher, condition)))
        // .withFailureHandler(spyFailureHandler)
        .check(ViewAssertions.matches(condition))
}

//fun tryToAssertFirstView1(viewMatcher: Matcher<View>) {
//    onView(firstViewOf(allOf(viewMatcher, ViewMatchers.isDisplayed())))
//        // .withFailureHandler(spyFailureHandler)
//        .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//}

fun tryToAssert(viewMatcher: Matcher<View>, condition: Matcher<View>) {
    onView(viewMatcher)
        //.withFailureHandler(spyFailureHandler)
        .check(ViewAssertions.matches(condition))
}

//fun tryToAssert1(viewMatcher: Matcher<View>) {
//    onView(viewMatcher)
//        //.withFailureHandler(spyFailureHandler)
//        .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//}
//const val defaultWait = 2_000L
//fun assertDisplayed(viewId: Int) {
//    waitFor(viewId, defaultWait)
//    viewId.resourceMatcher().assertAny(isDisplayed())
//}
//
//fun assertDisplayed(text: String) {
//    withText(text).assertAny(isDisplayed())
//}
//
//fun assertDisplayed(viewId: Int, text: String) {
//    viewId.resourceMatcher().assertAny(withText(text))
//}
//
//fun assertErrorDisplayed(viewId: Int, text: String) {
//    viewId.resourceMatcher().assertAny(hasErrorText(text))
//}
