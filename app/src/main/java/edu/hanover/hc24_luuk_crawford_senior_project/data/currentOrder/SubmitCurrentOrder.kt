package edu.hanover.hc24_luuk_crawford_senior_project.data.currentOrder

import android.content.Context
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.zybooks.hc24_luuk_crawford_senior_project.R

fun submitCurrentOrder(onNavigateToOrders: () -> Unit, context: Context) {
    CurrentOrderManager.setOrderTime()

    val orderID = CurrentOrderManager.getCurrentOrderHash()
    Toast.makeText(
        context,
        R.string.tryingToSubmit, Toast.LENGTH_SHORT
    )
        .show()

    FirebaseFirestore.getInstance()
        .document("orders/${orderID}")
        .set(CurrentOrderManager.getCurrentUserOrder())
        .addOnSuccessListener {
            Toast.makeText(
                context,
                R.string.orderSubmitted, Toast.LENGTH_SHORT
            )
                .show()
            onNavigateToOrders()
        }
        .addOnFailureListener {
            Toast.makeText(
                context,
                R.string.connectionFailed, Toast.LENGTH_SHORT
            )
                .show()
        }
}