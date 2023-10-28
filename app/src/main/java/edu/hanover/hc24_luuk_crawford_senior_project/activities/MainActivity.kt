package edu.hanover.hc24_luuk_crawford_senior_project.activities


// ...

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.firebase.firestore.FirebaseFirestore
import com.zybooks.hc24_luuk_crawford_senior_project.R
import edu.hanover.hc24_luuk_crawford_senior_project.composables.AppNavHost
import edu.hanover.hc24_luuk_crawford_senior_project.composables.screens.OrdersScreen
import edu.hanover.hc24_luuk_crawford_senior_project.data.Customization
import edu.hanover.hc24_luuk_crawford_senior_project.data.ItemStatus
import edu.hanover.hc24_luuk_crawford_senior_project.data.UserOrder
import edu.hanover.hc24_luuk_crawford_senior_project.services.downloadMenuFirebase
import edu.hanover.hc24_luuk_crawford_senior_project.services.downloadMenuLocal

//This setups loading to make my composable's work.
//- Starts AppNavHost

//var mySelections = mutableListOf<String>()
val firebaseIsEnabled = true
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavHost()
        }


        if (firebaseIsEnabled) {
            downloadMenuFirebase()
        } else {
            downloadMenuLocal() }
    }
}
private val currentOrder = UserOrder("",0,0,0, Customization(),ItemStatus.inProgress,0,0)

fun getCurrentUserOrder(): UserOrder {
    return currentOrder
}
fun setUsersName(name: String){
    currentOrder.user = name
}

fun getUserName(): String {
    return currentOrder.user
}


fun setOrderItemID(id: Int){
    currentOrder.itemID = id
}

fun addOrderCustomization(customizationItem: String, customizationCategory: String){
    when (customizationCategory) {
        "toppings" -> currentOrder.customization.toppings.add(customizationItem)
        "sides" -> currentOrder.customization.sides.add(customizationItem)
    }
}

fun removeOrderCustomization(customizationItem: String, customizationCategory: String){
    when (customizationCategory) {
        "toppings" -> currentOrder.customization.toppings.remove(customizationItem)
        "sides" -> currentOrder.customization.sides.remove(customizationItem)
    }
}

fun submitCurrentOrder(onNavigateToOrders: () -> Unit, context: Context){
    setOrderTime()

    val orderID = getCurrentOrderHash()
    Toast.makeText(context,
        R.string.tryingToSubmit, Toast.LENGTH_SHORT)
        .show()

    FirebaseFirestore.getInstance()
        .document("orders/${orderID}")
        .set(getCurrentUserOrder())
        .addOnSuccessListener{
            Toast.makeText(context,
                R.string.orderSubmitted, Toast.LENGTH_SHORT)
                .show()
            onNavigateToOrders()
        }
        .addOnFailureListener { Toast.makeText(context,
            R.string.connectionFailed, Toast.LENGTH_SHORT)
            .show()}
}

fun getCurrentOrderHash(): Int {
    return getCurrentUserOrder().hashCode()
}

private fun setOrderTime() {
    currentOrder.orderTime = System.currentTimeMillis()
}

