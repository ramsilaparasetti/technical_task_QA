package com.test.news

import androidx.test.rule.ActivityTestRule
import com.test.news.Helpers.HelperMatchers.toggleDeviceInternet
import com.test.news.features.login.presentation.LoginActivity
import com.test.news.news.NewsRobot
import org.junit.Rule
import org.junit.Test

class NewsInstrumentedTests {

    @get:Rule
    var activityTestRule = ActivityTestRule<LoginActivity>(LoginActivity::class.java)

    /*
 <=====================================================================================================================>
                                   1 - As a user I want to log in to the app
 <=====================================================================================================================>
  */
    /*
    Scenario 1 - user opens the android app first time (when not logged in yet)
    Given - the user opens app for the first time (when not logged in yet)
    Then - login screen with user name and password entries and login button is displayed
*/
    @Test
    fun userOpensLoginScreen() {
        NewsRobot()
            .checkLoginScreen()
    }

    /*
    Scenario 2 - user login failed
    Given - the user provided wrong user name and/or password
    When - login button is clicked
    Then - error markers are displayed by user name and/or password entries
*/
    @Test
    fun userLoginFailed() {
        NewsRobot()
            .enterInValidUserName()
            .enterInValidPassWord()
            .clickLoginButton()
            .checkInvalidUserNameError()

            .enterValidUserName()
            .enterInValidPassWord()
            .clickLoginButton()
            .checkInvalidPassWordError()
    }

    /*
    Scenario 3 - user login succeed
    Given - the user provided right user name and password
    When - login button is clicked
    Then - user is taken to the news screen
*/
    @Test
    fun userLoginSucceed() {
        NewsRobot()
            .enterValidUserName()
            .enterValidPassWord()
            .clickLoginButton()
            .checkUserNavigatedToNewsScreen()
    }

    /*
    Scenario 4 - user opens app next time (when previously logged in)
    Given - the user opens app next time (when previously logged in)
    Then - user is taken straight to the news screen
*/
    @Test
    fun userOpensAppNextTime_PreviouslyLoggedIn() {
        NewsRobot()
            .checkLoginScreen(entireScreenValidation = false)
            .enterValidUserName()
            .enterValidPassWord()
            .clickLoginButton()
            .checkUserNavigatedToNewsScreen()
            .pressDeviceHome()
            .selectNewsAppFromRecents()
            .checkUserNavigatedToNewsScreen()
    }

    /*
    <=====================================================================================================================>
                                      2 - As a user I want to see my news
    <=====================================================================================================================>
     */
    /*
    Scenario 1 - news images are loaded
    Given - the user successfully logged in to the app
    When - there is internet connection
    Then - images are displayed in the rows on the list (row can have one or more images scrollable horizontally)
*/
    @Test
    fun newsImagesLoaded_With_InternetOn() {
        toggleDeviceInternet(internetON = true)
        NewsRobot()
            .checkLoginScreen(entireScreenValidation = false)
            .enterValidUserName()
            .enterValidPassWord()
            .clickLoginButton()
            .checkNewsImagesDisplayed()
    }

    /*
    Scenario 2 - failed to load images
    Given - the user successfully logged in to the app
    When - there is no internet connection
    Then - “failed to load news” error message is displayed and Retry button
*/
    @Test
    fun newsImagesFailedToLoad_With_InternetOff() {
        toggleDeviceInternet(internetON = false)
        NewsRobot()
            .checkLoginScreen(entireScreenValidation = false)
            .enterValidUserName()
            .enterValidPassWord()
            .clickLoginButton()
            .checkNoNewsImagesDisplayed()
        toggleDeviceInternet(internetON = true)
    }

    /*
    Scenario 3 - news image is clicked
    Given - the news images are successfully loaded on the screen
    When - the user clicks one of the image
    Then - user is navigated to the external browser with clicked image loaded
*/
    @Test
    fun newsImagesAreNavigatedToBrowser_WhenClicked() {
        NewsRobot()
            .checkLoginScreen(entireScreenValidation = false)
            .enterValidUserName()
            .enterValidPassWord()
            .clickLoginButton()
        /*
        Below methods created to perform click action on Recycler View and validate content from the web view,
        how ever project running into build errors hence left them with place holder content.
        */
            .clickOnFirstNewsImage()
            .checkNavigationToBrowser()
            .checkBrowserContent()
    }
}




