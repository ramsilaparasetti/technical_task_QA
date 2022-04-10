package com.test.news

import androidx.test.rule.ActivityTestRule
import com.test.news.features.login.presentation.LoginActivity
import com.test.news.login.LoginRobot
import org.junit.Rule
import org.junit.Test
import java.lang.Thread.sleep

class xLoginInstrumentedTest {

    @get:Rule
    var activityTestRule = ActivityTestRule<LoginActivity>(LoginActivity::class.java)

    @Test
    fun userOpensLoginScreen() {
        LoginRobot()
            .checkLoginScreen()
    }

    @Test
    fun userLoginFailed() {
        LoginRobot()
            .enterInValidUserName()
            .enterInValidPassWord()
            .clickLoginButton()
            .checkInvalidUserNameError()

            .enterValidUserName()
            .enterInValidPassWord()
            .clickLoginButton()
            .checkInvalidPassWordError()
    }

    @Test
    fun userLoginSucceed() {
        LoginRobot()
            .enterValidUserName()
            .enterValidPassWord()
            .clickLoginButton()
            .checkNewsImagesDisplayed()
    }

    @Test
    fun userOpensAppNextTime_PreviouslyLoggedIn() {
        LoginRobot()
            .checkLoginScreen(screenValidation = false)
            .enterValidUserName()
            .enterValidPassWord()
            .clickLoginButton()
            //.checkNewsImagesDisplayed()
                sleep(5000)
                        LoginRobot()
            .pressDeviceHome()
            .selectNewsAppFromRecents()
            .checkNewsImagesDisplayed()
    }
//
//    @Test
//    fun loginWithInvalidUserName() {
//
//    }
//
//    @Test
//    fun loginWithInvalidPassWord() {
//
//    }
//
//    @Test
//    fun shouldLoginWithValidCredentials() {
////        InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand("svc wifi enable")
////        InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand("svc data enable")
//        onView(withId(userNameField))
//            .perform(clearText(), typeText(VALID_USER_NAME))
//        onView(withId(passWordField))
//            .perform(clearText(), typeText(VALID_USER_PASSWORD))
//        onView(withId(loginButton))
//            .perform(click())
//
//        // TODO assert login when ready
//        assertTrue(activityTestRule.activity.isFinishing)
//        sleep(10000)
//        //onView(withId(R.id.recyclerViewImageWidget)).check(matches(isDisplayed()))
//        // onView(withId(R.id.recyclerViewImageWidget)).exists()
//        R.id.recyclerViewImageWidget.resourceMatcher().assertAny(isDisplayed())
//
//    }

//    companion object {
//        private const val userNameField = R.id.editTextUserName
//        private const val passWordField = R.id.editTextPassword
//        private const val loginButton = R.id.buttonLogin
//        private const val VALID_USER_NAME = "user1"
//        private const val VALID_USER_PASSWORD = "password"
//    }
}



