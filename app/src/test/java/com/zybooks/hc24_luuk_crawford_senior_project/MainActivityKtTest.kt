package com.zybooks.hc24_luuk_crawford_senior_project


import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotSame
import org.junit.Before
import org.junit.Rule
import org.junit.Test
//import org.robolectric.RobolectricTestRunner

//@RunWith(RobolectricTestRunner::class)
internal class MainActivityKtTest {
    private lateinit var navController: TestNavHostController

//valcomposeTestRule = createComposeRule()

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            AppNavHost(navController = navController)
        }
    }


    @Test
    fun newMenuListTest() {
        val testMenuList = mutableListOf<MenuItem>()
        assertEquals(testMenuList, myMenuList)
    }

    @Test
    fun fillMenuListTest() {
        val testMenuList = mutableListOf<MenuItem>()
        val itemExample = MenuItem(
            name = "VeggieBurgerTest",
            id = 101,
            customizationType = "Burger",
            imageLink = "https://i.imgur.com/K6alfDv.jpeg"
        )
        myMenuList.add(itemExample)
        assertNotSame(testMenuList, myMenuList)

        testMenuList.add(itemExample)
        assertEquals(testMenuList, myMenuList)
    }

    @Test
    fun createItemFromFoodTest() {
        val food = mapOf(
            "name" to "candy Apple",
            "id" to 300,
            "customizationType" to "burger",
            "imageLink" to "www.something../jpeg"
        )
        val createdItem = createItemFrom(food)

        //ensure created correctly
        val testItemSame = MenuItem("candy Apple", 300, "burger", "www.something../jpeg")
        assertEquals(testItemSame, createdItem)

        //making sure it's not always returning same
        val testItemDifferent = MenuItem()
        assertNotSame(testItemDifferent, createdItem)
    }

    @Test
    fun createItemFromFoodTestSmallDifferences(){
        val food = mapOf(
            "name" to "veggie Burger",
            "id" to 10,
            "customizationType" to "burger",
            "imageLink" to "www.something../jpeg"
        )
        val createdItem = createItemFrom(food)

        //different ID
        val testItemDifferentID = MenuItem("veggie Burger", 22, "burger", "www.something../jpeg")
        assertNotSame(testItemDifferentID, createdItem)

        //different name string
        val testItemDifferentName = MenuItem("not apple", 10, "burger", "www.something../jpeg")
        assertNotSame(testItemDifferentName, createdItem)
    }

//todo: not working cuz document weird
    @Test
    fun listDocumentContentsInTest(){
        val pretendDB = mutableMapOf<String, MutableMap<String, Any?>>()

        val hamburgerMap = mutableMapOf(
            "customizationType" to "Burger",
            "id" to 100,
            "imageLink" to "https://i.imgur.com/N22z5gY.jpeg",
            "name" to "Hamburger"
        )
        //listDocumentContentsIn(documentExample, listTest)
        //pretendDB["Burger"] = hamburgerMap

    }
        @Test
        fun locationInfoLogoTest(){

            composeTestRule.setContent {
                val infoLogo = locationInfoLogoTest()
            }
            val button = composeTestRule.onNode(hasTestTag("yourTestTag"), true)

            button.assertIsDisplayed()
            button.performClick()
        }
//TESTINGGGGGGGG

        @Test
        fun beginOrderButtonTest(){

        composeTestRule.setContent {
            AppNavHost(Routes.welcomeScreen.name)
        }
            val button = composeTestRule.onNode(hasTestTag("welcomeToMenuButton"), true)

            button.assertIsDisplayed()
            button.performClick()
        }



*/


}

