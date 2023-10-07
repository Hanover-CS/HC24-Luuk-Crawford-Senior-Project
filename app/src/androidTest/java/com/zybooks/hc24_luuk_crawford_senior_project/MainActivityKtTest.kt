package com.zybooks.hc24_luuk_crawford_senior_project


import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
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
    //private lateinit var navController: TestNavHostController

    @get:Rule
    val composeTestRule = createComposeRule() //createAndroidComposeRule<ComponentActivity>()

    @Before
    fun onlySomethingInList(){
        myMenuList.clear()
        myMenuList.add(MenuItem())
    }


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
        //putSomethingInList()
        composeTestRule.setContent {
            //MenuScreen()
            AppNavHost(Routes.menuScreen.name)
        }
        checkMenuScreenContents()
    }

    private fun checkMenuScreenContents() {
        val hcLogoText = composeTestRule.onNode(hasTestTag("collegeNameText"))
        hcLogoText.assertIsDisplayed()
        val otherPageUndergroundText = composeTestRule.onNode(hasTestTag("undergroundText"))
        otherPageUndergroundText.assertDoesNotExist()

        val itemExistsInList = composeTestRule.onNode(hasTestTag("item0Exists"))
        itemExistsInList.assertIsDisplayed()

        val noOtherItemsInList = composeTestRule.onNode(hasTestTag("item1Exists"))
        noOtherItemsInList.assertDoesNotExist()
    }

    @Test
    fun navigateFromWelcomeToMenu(){
        composeTestRule.setContent {
            AppNavHost(Routes.welcomeScreen.name)
        }
        val beginOrderButton = composeTestRule.onNode(hasTestTag("beginOrderButton"), true)
        beginOrderButton.assertExists()
        beginOrderButton.assertIsDisplayed()
        beginOrderButton.assertHasClickAction()
        beginOrderButton.performClick()
        checkMenuScreenContents()
        beginOrderButton.assertDoesNotExist()
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

