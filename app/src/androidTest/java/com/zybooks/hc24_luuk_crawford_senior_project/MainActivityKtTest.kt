package com.zybooks.hc24_luuk_crawford_senior_project


import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

//import org.robolectric.RobolectricTestRunner

//@RunWith(RobolectricTestRunner::class)
@RunWith(AndroidJUnit4::class)
internal class MainActivityKtTest {
    private lateinit var navController: TestNavHostController

//valcomposeTestRule = createComposeRule()

    @get:Rule
    val composeTestRule = createComposeRule() //createAndroidComposeRule<ComponentActivity>()


    fun putSomethingInList(){
        myMenuList.add(MenuItem())
    }

    /*
    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            AppNavHost(navController = navController)
        }
    }*/


    //@RunWith(AndroidJUnit4::class)

    @Test
    fun useAppContext() {
            // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.zybooks.hc24_luuk_crawford_senior_project", appContext.packageName)
    }


    @Test
    fun welcomeScreenExists(){
        composeTestRule.setContent {
            WelcomeScreen {

            }
        }
        val beginOrderButton = composeTestRule.onNode(hasTestTag("beginOrderButton"), true)
        beginOrderButton.assertIsDisplayed()

        val locationInfoLogoText = composeTestRule.onNode(hasTestTag("collegeNameText"), true)
        locationInfoLogoText.assertIsDisplayed()

        val undergroundText = composeTestRule.onNode(hasTestTag("undergroundText"))
        undergroundText.assertIsDisplayed()
    }

    @Test
    fun menuScreenExists(){
        putSomethingInList()
        composeTestRule.setContent {
            MenuScreen()
        }
        val hcLogoText = composeTestRule.onNode(hasTestTag("collegeNameText"))
        hcLogoText.assertIsDisplayed()
        val otherPageUndergroundText = composeTestRule.onNode(hasTestTag("undergroundText"))
        otherPageUndergroundText.assertDoesNotExist()

        val itemExistsInList = composeTestRule.onNode(hasTestTag("anItemExists"))
        itemExistsInList.assertIsDisplayed()
    }





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

