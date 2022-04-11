package com.test.news.news

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.test.news.Helpers.HelperMatchers.assertDisplayed
import com.test.news.Helpers.HelperMatchers.assertErrorDisplayed
import com.test.news.Helpers.HelperMatchers.clickOnImageView
import com.test.news.Helpers.HelperMatchers.uiDevice
import com.test.news.Helpers.HelperMatchers.waitFor
import com.test.news.news.NewsElements.INVALID_USER_NAME
import com.test.news.news.NewsElements.INVALID_USER_PASSWORD
import com.test.news.news.NewsElements.PASS_WORD_ERROR_TEXT
import com.test.news.news.NewsElements.USER_NAME_ERROR_TEXT
import com.test.news.news.NewsElements.VALID_USER_NAME
import com.test.news.news.NewsElements.VALID_USER_PASSWORD
import com.test.news.news.NewsElements.loginButton
import com.test.news.news.NewsElements.newsFeed
import com.test.news.news.NewsElements.newsImageWidget
import com.test.news.news.NewsElements.newsLogo
import com.test.news.news.NewsElements.newsLogoText
import com.test.news.news.NewsElements.passWordField
import com.test.news.news.NewsElements.userNameField

class NewsRobot {

    private val NEWS_APP = "News"
    private val FAILED_LOAD_NEWS_TEXT = "Failed to load news"

    fun checkLoginScreen(entireScreenValidation: Boolean = true) = apply {
        assertDisplayed(newsLogo, newsLogoText)
        if (entireScreenValidation) {
            assertDisplayed(userNameField)
            assertDisplayed(passWordField)
            assertDisplayed(loginButton)
        }
    }

    fun checkInvalidUserNameError() = apply {
        assertErrorDisplayed(userNameField, USER_NAME_ERROR_TEXT)
    }

    fun checkInvalidPassWordError() = apply {
        assertErrorDisplayed(passWordField, PASS_WORD_ERROR_TEXT)
    }

    fun checkUserNavigatedToNewsScreen() = apply {
        assertDisplayed(newsFeed)
    }

    fun checkNewsImagesDisplayed() = apply {
        assertDisplayed(newsImageWidget)
    }

    fun checkNoNewsImagesDisplayed() = apply {
        assertDisplayed(FAILED_LOAD_NEWS_TEXT)
    }

   // Below function created to perform click action on Recycler View how ever it is running into build errors
    fun clickOnFirstNewsImage() =apply {
        clickOnImageView(0, newsImageWidget)
    }

    fun checkNavigationToBrowser() = apply {
        //place holder function check user navigated to web view
    }

    fun checkBrowserContent() = apply {
        //place holder function check image loaded on browser matched with the Image view
    }
    fun enterValidUserName() = apply {
        enterText(userNameField, VALID_USER_NAME)
}
    fun enterValidPassWord() = apply {
        enterText(passWordField, VALID_USER_PASSWORD)
    }

    fun enterInValidUserName() = apply {
        enterText(userNameField, INVALID_USER_NAME)
    }
    fun enterInValidPassWord() = apply {
        enterText(passWordField, INVALID_USER_PASSWORD)
    }

    fun clickLoginButton() = apply {
        onView(withId(loginButton))
            .perform(click())
    }

    private fun enterText(id: Int, text: String){
        onView(withId(id))
            .perform(clearText(), typeText(text))
    }

    fun pressDeviceHome() = apply {
        uiDevice.pressHome()
    }

    fun selectNewsAppFromRecents() = apply {
        uiDevice.pressRecentApps()
        waitFor(NEWS_APP)
        uiDevice.pressRecentApps()
    }
}
