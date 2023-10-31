package edu.hanover.hc24_luuk_crawford_senior_project.composables.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.zybooks.hc24_luuk_crawford_senior_project.R
import edu.hanover.hc24_luuk_crawford_senior_project.composables.SubmitOrderButton
import edu.hanover.hc24_luuk_crawford_senior_project.composables.createCheckboxForEach
import edu.hanover.hc24_luuk_crawford_senior_project.composables.imageAndTextFor
import edu.hanover.hc24_luuk_crawford_senior_project.data.MenuData.Companion.getCustomizationOfItemID
import edu.hanover.hc24_luuk_crawford_senior_project.data.MenuData.Companion.getMenuItemFromOrderID
import edu.hanover.hc24_luuk_crawford_senior_project.data.UserOrder

/**
 * Toppings screen
 * @param onNavigateToOrders needs function to change page to Orders.
 * @param order the order it needs to load customizations for.
 */
@Composable
fun ToppingsScreen(onNavigateToOrders: () -> Unit,order: UserOrder) {
    val itemCustomization = getCustomizationOfItemID(order.itemID)
    Column {
        Text(text = "This is the toppings screen!")
        imageAndTextFor(menuItem = getMenuItemFromOrderID(order.itemID))

        Text(text = stringResource(R.string.toppings))
        createCheckboxForEach(itemCustomization.toppings, "toppings")

        Text(text = stringResource(R.string.sides))
        createCheckboxForEach(itemCustomization.sides, "sides")

        SubmitOrderButton(onNavigateToOrders)
    }
}

