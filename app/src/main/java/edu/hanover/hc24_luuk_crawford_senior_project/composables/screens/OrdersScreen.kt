package edu.hanover.hc24_luuk_crawford_senior_project.composables.screens

import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
//import edu.hanover.hc24_luuk_crawford_senior_project.activities.downloadedList
import edu.hanover.hc24_luuk_crawford_senior_project.data.Customization
import edu.hanover.hc24_luuk_crawford_senior_project.data.ItemStatus
import edu.hanover.hc24_luuk_crawford_senior_project.data.UserOrder
var downloadedList = mutableListOf<UserOrder>()
/**
 * View current active orders
 * TODO: THIS SECTION WIP not cleaned
 */
@Composable
fun OrdersScreen(onNavigateToOrders: () -> Unit) {
    LazyColumn(){
        if (downloadedList.size >= 1) {
            Log.d(ContentValues.TAG, "showOrders!")
           
                item {
                    Spacer(modifier = Modifier.height(40.dp))
                }
                for (order in downloadedList) {

                    item {
                        Column(
                            Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        )
                        {
                            Text(text = "_____________________________")
                            Text(text = order.user)
                            Text(text = order.itemID.toString())
                            Text(text = order.customization.sides.toString())
                            Text(text = order.customization.toppings.toString())
                            Text(text = order.orderTime.toString())
                            Text(text = "_____________________________")
                        }


                    }


            }
        } else {
            item{
                Text(text = "loading")
            }
            downloadOrders(onNavigateToOrders)
        }
    }





}

private fun downloadOrders(onNavigateToOrders: () -> Unit){
    //var complete = false
    //var mylist = mutableListOf<UserOrder>()
    val db = FirebaseFirestore.getInstance()
    val docRef = db.collection("orders")
    docRef.get()
        .addOnSuccessListener { document ->
            if (document != null) {
                downloadedList = listOrders(document)
                Log.d(ContentValues.TAG, "DOC SUCCESS!")
                onNavigateToOrders()
            } else {
                Log.d(ContentValues.TAG, "No such document")

            }
        }
        .addOnFailureListener { exception ->
            Log.d(ContentValues.TAG, "get failed with ", exception)
        }
}
/**
 * Creates UserOrders and returns them as list from QuerySnapshot.
 * @param document QuerySnapshot from firebase
 * @return mutable list of UserOrder
 */
fun listOrders(document: QuerySnapshot): MutableList<UserOrder> {

    Log.d(ContentValues.TAG, "TEST ${document} is food document")
    val orderList = mutableListOf<UserOrder>()
    for (order in document) {
        val orderData = order.data
        Log.d(ContentValues.TAG, "TEST ${orderData} is food id${orderData["id"]}")
        val createdOrder = createUserOrderFrom(orderData)

        orderList.add(createdOrder)

    }
    return orderList
}

/**
 * creates a OrderItem using the map of info.
 * @param order map which contains user order in map
 * @return returns UserOrder it created
 */
fun createUserOrderFrom(order: Map<String, Any>): UserOrder {
    val customizationMap = order["customization"] as Map<String, Any>
    val itemStatus = when (order["itemStatus"]) {
        "inProgress" -> ItemStatus.inProgress
        "complete" -> ItemStatus.complete
        "cancelled" -> ItemStatus.cancelled
        else -> ItemStatus.inProgress
    }
    val orderTime = order["orderTime"] as com.google.firebase.Timestamp
    val orderTimeFixed = orderTime.toDate()

    val endTime = order["orderEndTime"] as? com.google.firebase.Timestamp
    val endTimeFixed = endTime?.toDate()

    return UserOrder(
        user = "${order["user"]}",
        userID = "${order["userID"]}".toInt(),
        orderID = "${order["orderID"]}".toInt(),
        itemID = "${order["itemID"]}".toInt(),
        customization = Customization(
            customizationMap["sides"] as MutableList<String>,
            customizationMap["toppings"] as MutableList<String>
        ),
        itemStatus = itemStatus,
        orderTime = orderTimeFixed,
        orderEndTime = endTimeFixed
    )
}
