package edu.hanover.hc24_luuk_crawford_senior_project.composables.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.zybooks.hc24_luuk_crawford_senior_project.R
import edu.hanover.hc24_luuk_crawford_senior_project.composables.SubmitOrderButton
import edu.hanover.hc24_luuk_crawford_senior_project.composables.createCheckboxForEach
import edu.hanover.hc24_luuk_crawford_senior_project.composables.imageAndTextFor
import edu.hanover.hc24_luuk_crawford_senior_project.data.MenuData.Companion.getCustomizationOptionsOfItemID
import edu.hanover.hc24_luuk_crawford_senior_project.data.MenuData.Companion.getMenuItemFromItemID
import edu.hanover.hc24_luuk_crawford_senior_project.data.UserOrder
import edu.hanover.hc24_luuk_crawford_senior_project.data.currentOrder.CurrentOrderManager

/**
 * Toppings screen
 * @param onNavigate needs function to change page.
 * @param order the order it needs to load customizations for.
 */
@Composable
fun ToppingsScreen(onNavigate: () -> Unit, order: UserOrder) {
    val itemCustomization = getCustomizationOptionsOfItemID(order.itemID)
    LazyColumn {
        item{imageAndTextFor(menuItem = getMenuItemFromItemID(order.itemID))}
        item{loadCheckboxesOf(itemCustomization.toppings, R.string.toppings, "toppings") }
        item{loadCheckboxesOf(itemCustomization.sides, R.string.sides, "sides")}
        item{loadCheckboxesOf(itemCustomization.sauces, R.string.sauces, "sauces")}
        item{loadCheckboxesOf(itemCustomization.glutenFree, R.string.glutenFree, "glutenFree")}
        item{SubmitOrderButton(onNavigate)}
    }
    CurrentOrderManager.clearSelections()
}

@Composable
private fun loadCheckboxesOf(
    itemList: MutableList<String>,
    stringResource: Int,
    category: String
) {
    if (itemList.size > 0) {
        Text(text = stringResource(stringResource),
            style = TextStyle(fontSize = 25.sp),
            fontWeight = FontWeight.Bold)
        createCheckboxForEach(itemList = itemList, customizationCategory = category)
    }
}



