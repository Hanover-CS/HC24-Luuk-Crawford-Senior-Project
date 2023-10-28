package edu.hanover.hc24_luuk_crawford_senior_project.composables.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zybooks.hc24_luuk_crawford_senior_project.R
import edu.hanover.hc24_luuk_crawford_senior_project.activities.firebaseIsEnabled
import edu.hanover.hc24_luuk_crawford_senior_project.activities.submitCurrentOrder
import edu.hanover.hc24_luuk_crawford_senior_project.composables.createCheckboxForEach
import edu.hanover.hc24_luuk_crawford_senior_project.composables.imageAndTextFor
import edu.hanover.hc24_luuk_crawford_senior_project.data.MenuData.Companion.getCustomizationOfItemID
import edu.hanover.hc24_luuk_crawford_senior_project.data.MenuData.Companion.getMenuItemFromOrderID
import edu.hanover.hc24_luuk_crawford_senior_project.data.UserOrder

//start order, ID,
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

@Composable
fun SubmitOrderButton(onNavigateToOrders: () -> Unit) {
    val context = LocalContext.current
    val orderSubmitted = stringResource(R.string.orderSubmitted)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                if (firebaseIsEnabled){
                    submitCurrentOrder(onNavigateToOrders, context)

                }


            },
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier.testTag("submitOrderButton")
        ) {
            Text(
                text = stringResource(id = R.string.submitOrder),
                fontSize = 30.sp,
                color = Color.White, textAlign = TextAlign.Center
            )
        }
    }



}
