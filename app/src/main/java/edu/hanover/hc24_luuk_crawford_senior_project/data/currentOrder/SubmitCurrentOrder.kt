package edu.hanover.hc24_luuk_crawford_senior_project.data.currentOrder

import android.content.Context
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.zybooks.hc24_luuk_crawford_senior_project.R

/**
 * adds order to database.
 * notifies user with toast popups.
 * navigates to Orders page on success
 * @param onNavigateToOrders needs function to change page to Orders.
 * @param context where the toast popup should happen
 */

fun submitCurrentOrder(onNavigateToOrders: () -> Unit, context: Context) {
    CurrentOrderManager.setCurrentOrderTime()

    val orderID = CurrentOrderManager.getCurrentOrderHash()

    val showTryingToSubmitToast = Toast.makeText(
        context,
        R.string.tryingToSubmit, Toast.LENGTH_LONG
    )
    showTryingToSubmitToast.show()

    FirebaseFirestore.getInstance()
        .document("orders/${orderID}")
        .set(CurrentOrderManager.getCurrentUserOrder())
        .addOnSuccessListener {
            showTryingToSubmitToast.cancel()//TODO: either allow cancel (UI stuff) or only data
            toastShow(context, R.string.orderSubmitted)
            onNavigateToOrders()//TODO: removethis replace with ordersscreenloading?
        }
        .addOnFailureListener {
            toastShow(context, R.string.connectionFailed)
        }
}
//TODO: refactor to diff files if I keep this
fun toastShow(context: Context, rDotStringDotMessage: Int) {
    Toast.makeText(
        context,
        rDotStringDotMessage, Toast.LENGTH_LONG
    )
        .show()
}