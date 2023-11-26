package edu.hanover.hc24_luuk_crawford_senior_project

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
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

    @Test
    fun menuToToppingsPage() {
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

    private fun navigateToToppingsWith(itemToClick: String) {
        val itemExistsInList = composeTestRule.onNode(hasTestTag(itemToClick))
        itemExistsInList.assertIsDisplayed()
        itemExistsInList.assertHasClickAction()
        itemExistsInList.performClick()
        itemExistsInList.assertIsNotDisplayed()
    }

    @Test
    fun menuTestSingleCheckbox() {
        composeTestRule.setContent {
            AppNavHost(Destination.menuScreen.name)
        }
        navigateToToppingsWith("ExampleName")
        testCheckbox("Bacon", "toppings")
        testCheckbox("Tater Tots", "sides")
    }

    private fun testCheckbox(checkboxTestTag: String, customizationCategory: String) {
        val checkbox = composeTestRule.onNode(hasTestTag(checkboxTestTag))
        assert(receiveCustomCategoryList(customizationCategory).isEmpty())

        checkbox.performClick()
        checkbox.performClick()
        assertEquals(
            mutableListOf(checkboxTestTag),
            receiveCustomCategoryList(customizationCategory)
        )
        checkbox.performClick()
        assert(
            receiveCustomCategoryList(customizationCategory).isEmpty()
        )
    }

    private fun receiveCustomCategoryList(
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

}