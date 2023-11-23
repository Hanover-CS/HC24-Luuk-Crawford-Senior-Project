package edu.hanover.hc24_luuk_crawford_senior_project.composables.screens

import android.content.ContentValues
import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import edu.hanover.hc24_luuk_crawford_senior_project.data.ActiveOrders
//import edu.hanover.hc24_luuk_crawford_senior_project.activities.downloadedList
import edu.hanover.hc24_luuk_crawford_senior_project.data.Customization
import edu.hanover.hc24_luuk_crawford_senior_project.data.ItemStatus
import edu.hanover.hc24_luuk_crawford_senior_project.data.UserOrder
//var downloadedList = mutableListOf<UserOrder>()
/**
 * View current active orders
 * TODO: THIS SECTION WIP not cleaned
 */
@Composable
fun OrdersScreenLoading(onNavigateToOrders: () -> Unit) {
    Text(text = "LOADING ORDERS")
    downloadOrders(onNavigateToOrders)
}

private fun downloadOrders(onNavigateToOrders: () -> Unit){
    Log.d(ContentValues.TAG, "DOWNLOADING ORDERS")
    val db = FirebaseFirestore.getInstance()
    val docRef = db.collection("orders")
    docRef.get()
        .addOnSuccessListener { document ->
            if (document != null) {
                updateOrders(document)
                Log.d(ContentValues.TAG, "DOC SUCCESS, now navigate!")
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
fun updateOrders(document: QuerySnapshot){
    Log.d(ContentValues.TAG, "UPDATING ORDERS LIST")
    ActiveOrders.clearActiveOrdersList()
    Log.d(ContentValues.TAG, "TEST ${document} is food document")
    for (order in document) {
        Log.d(ContentValues.TAG, "${order} in document")
        val orderData = order.data
        Log.d(ContentValues.TAG, "TEST ${orderData} is food id${orderData["id"]}")
        val createdOrder = createUserOrderFrom(orderData)
        ActiveOrders.addActiveOrder(createdOrder)
    }
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
    val orderTime = order["orderTime"]

    val endTime = order["orderEndTime"]

    return UserOrder(
        user = order["user"] as String,
        userID = order["userID"] as Long,
        orderID = order["orderID"] as Long,
        itemID = order["itemID"] as Long,
        customization = Customization(
            customizationMap["sides"] as MutableList<String>,
            customizationMap["toppings"] as MutableList<String>
        ),
        itemStatus = itemStatus,
        orderTime = orderTime as Long,
        orderEndTime = endTime as Long
    )
}

