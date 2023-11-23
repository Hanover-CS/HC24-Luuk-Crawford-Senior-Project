package edu.hanover.hc24_luuk_crawford_senior_project.data.currentOrder

import android.content.Context
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.zybooks.hc24_luuk_crawford_senior_project.R

/**
 * adds order to database.
 * notifies user with toast popups.
 * navigates to Orders page on success
 * @param onNavigate needs function to change page to Orders.
 * @param context where the toast popup should happen
 */
fun submitCurrentOrder(onNavigate: () -> Unit, context: Context) {
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
            showTryingToSubmitToast.cancel()
            toastShow(context, R.string.orderSubmitted)
            onNavigate()
        }
        .addOnFailureListener {
            toastShow(context, R.string.connectionFailed)
        }
}
