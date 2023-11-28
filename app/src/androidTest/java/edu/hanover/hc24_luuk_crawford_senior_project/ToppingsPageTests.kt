package edu.hanover.hc24_luuk_crawford_senior_project

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.test.espresso.Espresso.pressBack
import androidx.test.ext.junit.runners.AndroidJUnit4
import edu.hanover.hc24_luuk_crawford_senior_project.composables.AppNavHost
import edu.hanover.hc24_luuk_crawford_senior_project.data.Customization
import edu.hanover.hc24_luuk_crawford_senior_project.data.Destination
import edu.hanover.hc24_luuk_crawford_senior_project.data.MenuData
import edu.hanover.hc24_luuk_crawford_senior_project.data.MenuItem
import edu.hanover.hc24_luuk_crawford_senior_project.data.currentOrder.CurrentOrderManager
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class ToppingsPageTests {
    @get:Rule
    val composeTestRule = createComposeRule()

    /**
     * Clears any changes then adds default MenuItem for each test.
     */
    @Before
    fun itemsInMenu() {
        MenuData.clearMenuContents()
        MenuData.addMenuItem(
            MenuItem(
                "ExampleName",
                300,
                "Burger",
                "https://i.imgur.com/BDa36Zp.jpeg"
            )
        )
        MenuData.addMenuItem(
            MenuItem(
                "ExampleTwo",
                350,
                "Burger",
                "https://i.imgur.com/BDa36Zp.jpeg"
            )
        )
        val burgerSides = mutableListOf("Hand Cut Fries Test", "Mac-N-Cheese", "Tater Tots")
        val burgerToppings =
            mutableListOf("Lettuce Test", "Tomato", "Onion Test", "Pickle", "Cheese", "Bacon")
        MenuData.setItemTypeToCustomization(
            "Burger",
            Customization(burgerSides, burgerToppings)
        )
        MenuData.addMenuItem(MenuItem())
    }

    /**
     * Loads menuScreen, navigates to toppings.
     * Confirms toppings contents loaded correctly.
     */
    @Test
    fun menuToToppingsPageTest() {
        composeTestRule.setContent {
            AppNavHost(Destination.menuScreen.name)
        }
        navigateToToppingsWith("ExampleName")
        toppingsPageContentsExist()
    }




    private fun toppingsPageContentsExist() {
        confirmCheckboxExists("Lettuce Test")
        confirmCheckboxExists("Tomato")
        confirmCheckboxExists("Onion Test")
        confirmCheckboxExists("Pickle")
        confirmCheckboxExists("Cheese")
        confirmCheckboxExists("Bacon")

        confirmCheckboxExists("Hand Cut Fries Test")
        confirmCheckboxExists("Mac-N-Cheese")
        confirmCheckboxExists("Tater Tots")

        val submitOrderButton = composeTestRule.onNode(hasTestTag("submitOrderButton"))

        composeTestRule.onNode(hasTestTag("Cheese")).performTouchInput {
            down(center)
            moveBy(Offset(0f, -300f))
            up()
        }

        submitOrderButton.assertExists()
        submitOrderButton.assertIsDisplayed()
        submitOrderButton.assertIsEnabled()
        submitOrderButton.assertHasClickAction()
    }

    private fun confirmCheckboxExists(checkboxName: String) {
        val checkbox = composeTestRule.onNode(hasTestTag(checkboxName))
        checkbox.assertIsDisplayed()
        checkbox.assertHasClickAction()
    }

    /**
     * Clicks button to navigate then makes sure it is no longer displayed.
     */
    private fun navigateToToppingsWith(itemToClick: String) {
        val itemExistsInList = composeTestRule.onNode(hasTestTag(itemToClick))
        itemExistsInList.assertIsDisplayed()
        itemExistsInList.assertHasClickAction()
        itemExistsInList.performClick()
        itemExistsInList.assertIsNotDisplayed()
    }

    /**
     * Test to make sure a few checkboxes are interact correctly.
     */
    @Test
    fun menuTestSingleCheckboxTest() {
        composeTestRule.setContent {
            AppNavHost(Destination.menuScreen.name)
        }
        navigateToToppingsWith("ExampleName")
        testCheckbox("Bacon", "toppings")
        testCheckbox("Tater Tots", "sides")
    }

    private fun testCheckbox(checkboxTestTag: String, category: String) {
        val checkbox = composeTestRule.onNode(hasTestTag(checkboxTestTag))
        assert(receiveCategoryList(category).isEmpty())

        checkbox.performClick()
        checkbox.performClick()
        assertEquals(
            mutableListOf(checkboxTestTag),
            receiveCategoryList(category)
        )
        checkbox.performClick()
        assert(
            receiveCategoryList(category).isEmpty()
        )
    }

    /**
     * Exists to easily return either .toppings or .sides list.
     * @return customization category contents in list
     */
    private fun receiveCategoryList(
        customizationCategory: String
    ): MutableList<String> {
        var customList = mutableListOf<String>()
        when (customizationCategory) {
            "toppings" -> {
                customList = CurrentOrderManager.getCurrentUserOrder().customization.toppings
            }
            "sides" -> {
                customList = CurrentOrderManager.getCurrentUserOrder().customization.sides
            }
        }
        return customList
    }

    /**
     * Test to ensure CurrentOrderManager lists and checkboxes update correctly.
     */
    @Test
    fun checkboxesModifyListCorrectlyTest() {
        composeTestRule.setContent {
            AppNavHost(Destination.menuScreen.name)
        }
        navigateToToppingsWith("ExampleTwo")

        val checkboxLettuce = composeTestRule.onNode(hasTestTag("Lettuce Test"))
        val checkboxCheese = composeTestRule.onNode(hasTestTag("Cheese"))
        val checkboxTomato = composeTestRule.onNode(hasTestTag("Tomato"))
        val checkboxFries = composeTestRule.onNode(hasTestTag("Hand Cut Fries Test"))
        val checkboxMacNCheese = composeTestRule.onNode(hasTestTag("Mac-N-Cheese"))

        checkboxFries.performClick()
        checkboxMacNCheese.performClick()
        checkboxLettuce.performClick()
        assertEquals(
            mutableListOf("Hand Cut Fries Test", "Mac-N-Cheese"),
            CurrentOrderManager.getCurrentUserOrder().customization.sides
        )
        checkboxCheese.performClick()
        checkboxTomato.performClick()
        checkboxFries.performClick()
        assertEquals(mutableListOf("Lettuce Test", "Cheese", "Tomato"), CurrentOrderManager.getCurrentUserOrder().customization.toppings)
        checkboxLettuce.performClick()
        assertEquals(
            mutableListOf("Mac-N-Cheese"),
            CurrentOrderManager.getCurrentUserOrder().customization.sides
        )
        checkboxTomato.performClick()
        checkboxFries.performClick()
        assertEquals(
            mutableListOf("Cheese"),
            CurrentOrderManager.getCurrentUserOrder().customization.toppings
        )
        checkboxCheese.performClick()
        checkboxFries.performClick()
        assertEquals(
            mutableListOf<String>(),
            CurrentOrderManager.getCurrentUserOrder().customization.toppings
        )
    }
    @Test
    fun ensureSelectionsClearTest(){
        composeTestRule.setContent {
            AppNavHost(Destination.menuScreen.name)
        }
        navigateToToppingsWith("ExampleTwo")

        val checkboxMacNCheese = composeTestRule.onNode(hasTestTag("Mac-N-Cheese"))
        val checkboxFries = composeTestRule.onNode(hasTestTag("Hand Cut Fries Test"))
        val checkboxLettuce = composeTestRule.onNode(hasTestTag("Lettuce Test"))


        checkboxFries.performClick()
        checkboxMacNCheese.performClick()
        checkboxLettuce.performClick()
        assertEquals(
            mutableListOf("Hand Cut Fries Test", "Mac-N-Cheese"),
            CurrentOrderManager.getCurrentUserOrder().customization.sides
        )

        pressBack()
        assertEquals(
            mutableListOf<String>(),
            CurrentOrderManager.getCurrentUserOrder().customization.sides
        )
        assertEquals(
            mutableListOf<String>(),
            CurrentOrderManager.getCurrentUserOrder().customization.toppings
        )
    }
}