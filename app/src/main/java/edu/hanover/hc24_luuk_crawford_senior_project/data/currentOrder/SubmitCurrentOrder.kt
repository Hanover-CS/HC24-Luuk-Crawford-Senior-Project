package edu.hanover.hc24_luuk_crawford_senior_project.data.currentOrder

import android.content.Context
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.zybooks.hc24_luuk_crawford_senior_project.R
import edu.hanover.hc24_luuk_crawford_senior_project.data.currentOrder.CurrentOrderManager.getCurrentOrderTime
import java.util.Date

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
            showTryingToSubmitToast.cancel()
            Toast.makeText(
                context,
                R.string.orderSubmitted, Toast.LENGTH_LONG
            )
                .show()
            onNavigateToOrders()
        }
        .addOnFailureListener {
            Toast.makeText(
                context,
                R.string.connectionFailed, Toast.LENGTH_LONG
            )
                .show()
        }
}