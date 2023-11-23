package edu.hanover.hc24_luuk_crawford_senior_project.data.currentOrder

import android.content.Context
import android.widget.Toast

/**
 * @param message R.string.message
 */
fun toastShow(context: Context, message: Int) {
    Toast.makeText(
        context,
        message, Toast.LENGTH_LONG
    )
        .show()
}