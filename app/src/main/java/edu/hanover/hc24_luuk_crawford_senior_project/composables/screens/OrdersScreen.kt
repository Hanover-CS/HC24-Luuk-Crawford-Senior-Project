package edu.hanover.hc24_luuk_crawford_senior_project.composables.screens

import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zybooks.hc24_luuk_crawford_senior_project.R
import edu.hanover.hc24_luuk_crawford_senior_project.composables.imageAndTextFor
import edu.hanover.hc24_luuk_crawford_senior_project.data.ActiveOrders
import edu.hanover.hc24_luuk_crawford_senior_project.data.currentOrder.CurrentOrderManager

@Composable
fun OrdersScreen(onReloadOrders: () -> Unit){
    Text(text = "ORDERS SCREEN PLEASE STAY")

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ){
        Button(onClick = { onReloadOrders() }) {
            Text(
                text = stringResource(id = R.string.refresh),
                fontSize = 40.sp,
                color = Color.White, textAlign = TextAlign.Center
            )
        }

    }

    val activeOrderList = ActiveOrders.getActiveOrdersList()
    Log.d(ContentValues.TAG, "TESTING ActiveOrdersList ${activeOrderList}")
    LazyColumn(){
        items(activeOrderList){ item ->
            Box(){
                Row() {
                    Text(text = "User: ${item.user}")
                    Text(text = "Status: ${item.itemStatus}")
                }
                Row() {
                    Text(text = "Time Ordered: ${item.orderTime}")
                }
            }

        }
    }

}