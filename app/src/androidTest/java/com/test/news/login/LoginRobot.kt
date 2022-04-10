package com.test.news.login

import android.content.Context
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import com.test.news.HelperMatchers.assertDisplayed
import com.test.news.HelperMatchers.assertErrorDisplayed
import com.test.news.HelperMatchers.uiDevice
import com.test.news.HelperMatchers.waitFor
//import com.test.news.assertDisplayed
//import com.test.news.assertErrorDisplayed
//import com.test.news.base.GlideApp.get
import com.test.news.login.LoginElements.INVALID_USER_NAME
import com.test.news.login.LoginElements.INVALID_USER_PASSWORD
import com.test.news.login.LoginElements.PASS_WORD_ERROR_TEXT
import com.test.news.login.LoginElements.USER_NAME_ERROR_TEXT
import com.test.news.login.LoginElements.VALID_USER_NAME
import com.test.news.login.LoginElements.VALID_USER_PASSWORD
import com.test.news.login.LoginElements.loginButton
import com.test.news.login.LoginElements.newsImageWidget
import com.test.news.login.LoginElements.newsLogo
import com.test.news.login.LoginElements.newsLogoText
import com.test.news.login.LoginElements.passWordField
import com.test.news.login.LoginElements.userNameField
import java.lang.Thread.sleep

class LoginRobot {
    fun checkLoginScreen(screenValidation: Boolean = true) = apply {
        assertDisplayed(newsLogo, newsLogoText)
        if (screenValidation) {
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

    fun checkNewsImagesDisplayed() = apply {
        assertDisplayed(newsImageWidget)
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

    fun enterText(id: Int, text: String){
        onView(withId(id))
            .perform(clearText(), typeText(text))
    }

    fun pressDeviceHome() = apply {
        uiDevice.pressHome()
    }

    val NEWS_APP = "News"
    fun selectNewsAppFromRecents() = apply {
        uiDevice.pressRecentApps()
        //sleep(2000)
        waitFor(NEWS_APP)
        uiDevice.pressRecentApps()
    }
}
