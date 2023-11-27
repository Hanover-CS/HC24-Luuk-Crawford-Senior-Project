package edu.hanover.hc24_luuk_crawford_senior_project.composables.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.zybooks.hc24_luuk_crawford_senior_project.R
import edu.hanover.hc24_luuk_crawford_senior_project.composables.SubmitOrderButton
import edu.hanover.hc24_luuk_crawford_senior_project.composables.createCheckboxForEach
import edu.hanover.hc24_luuk_crawford_senior_project.composables.imageAndTextFor
import edu.hanover.hc24_luuk_crawford_senior_project.data.MenuData.Companion.getCustomizationOptionsOfItemID
import edu.hanover.hc24_luuk_crawford_senior_project.data.MenuData.Companion.getMenuItemFromItemID
import edu.hanover.hc24_luuk_crawford_senior_project.data.UserOrder

/**
 * Toppings screen
 * @param onNavigate needs function to change page.
 * @param order the order it needs to load customizations for.
 */
@Composable
fun ToppingsScreen(onNavigate: () -> Unit, order: UserOrder) {
    val itemCustomization = getCustomizationOptionsOfItemID(order.itemID)
    Column {
        imageAndTextFor(menuItem = getMenuItemFromItemID(order.itemID))

        loadCheckboxesOf(itemCustomization.toppings, R.string.toppings, "toppings")
        loadCheckboxesOf(itemCustomization.sides, R.string.sides , "sides")
        loadCheckboxesOf(itemCustomization.sauces, R.string.sauces , "sauces")
        loadCheckboxesOf(itemCustomization.glutenFree, R.string.glutenFree, "glutenFree")

        SubmitOrderButton(onNavigate)
    }
}

@Composable
private fun loadCheckboxesOf(
    itemList: MutableList<String>,
    stringResource: Int,
    category: String
) {
    if (itemList.size > 0) {
        Text(text = stringResource(stringResource))
        createCheckboxForEach(itemList = itemList, customizationCategory = category)
    }
}



