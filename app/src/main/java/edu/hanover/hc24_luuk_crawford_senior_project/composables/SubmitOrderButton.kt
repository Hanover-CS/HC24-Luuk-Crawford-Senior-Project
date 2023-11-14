package edu.hanover.hc24_luuk_crawford_senior_project.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import edu.hanover.hc24_luuk_crawford_senior_project.data.currentOrder.CurrentOrderManager
import edu.hanover.hc24_luuk_crawford_senior_project.data.currentOrder.submitCurrentOrder
import edu.hanover.hc24_luuk_crawford_senior_project.data.currentOrder.toastShow

/**
 * Creates button to submit order / navigate to Orders screen.
 * @param onNavigateToOrders needs function to change page to Orders.
 */
@Composable
fun SubmitOrderButton(onNavigateToOrders: () -> Unit) {
    val context = LocalContext.current

    var submitButtonEnabled by remember { mutableStateOf(true) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                submitButtonEnabled = false
                if (firebaseIsEnabled) {
                    if (25000 < (System.currentTimeMillis() - CurrentOrderManager.getCurrentOrderTime()!!.time)) {
                        submitCurrentOrder(onNavigateToOrders, context)
                    }
                    else{
                        toastShow(context, R.string.alreadyOrderedRecently)
                    }
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