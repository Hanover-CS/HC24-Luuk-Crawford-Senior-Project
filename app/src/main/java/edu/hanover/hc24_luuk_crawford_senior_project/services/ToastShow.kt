package edu.hanover.hc24_luuk_crawford_senior_project.services

import android.content.Context
import android.widget.Toast

/**
 * @param message R.string.message
 */
fun ToastShow(context: Context, message: Int) {
    Toast.makeText(
        context,
        message, Toast.LENGTH_LONG
    )
        .show()
}