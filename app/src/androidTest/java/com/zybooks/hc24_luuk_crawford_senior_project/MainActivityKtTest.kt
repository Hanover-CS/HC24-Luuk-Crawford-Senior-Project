package com.zybooks.hc24_luuk_crawford_senior_project


import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotSame
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

//import org.robolectric.RobolectricTestRunner

//@RunWith(RobolectricTestRunner::class)
internal class MainActivityKtTest {
    private lateinit var navController: TestNavHostController

//valcomposeTestRule = createComposeRule()

    @get:Rule
    val composeTestRule = createComposeRule() //createAndroidComposeRule<ComponentActivity>()
/*
    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            AppNavHost(navController = navController)
        }
    }*/


    @RunWith(AndroidJUnit4::class)
    class ExampleInstrumentedTest {
        @Test
        fun useAppContext() {
            // Context of the app under test.
            val appContext = InstrumentationRegistry.getInstrumentation().targetContext
            Assert.assertEquals("com.zybooks.hc24_luuk_crawford_senior_project", appContext.packageName)
        }
    }
/*
        @Test
        fun locationInfoLogoTest(){

            composeTestRule.setContent {
                val infoLogo = locationInfoLogoTest()
            }
            val button = composeTestRule.onNode(hasTestTag("yourTestTag"), true)

            button.assertIsDisplayed()
            button.performClick()
        }*/
//TESTINGGGGGGGG
/*
        @Test
        fun beginOrderButtonTest(){

        composeTestRule.setContent {
            beginOrderButton {

            }
            //AppNavHost(Routes.welcomeScreen.name)
        }
            val button = composeTestRule.onNode(hasTestTag("welcomeToMenuButton"), true)

            composeTestRule.onNodeWithText("").performClick()

            //button.assertIsDisplayed()
            //button.performClick()
        }
*/


}

