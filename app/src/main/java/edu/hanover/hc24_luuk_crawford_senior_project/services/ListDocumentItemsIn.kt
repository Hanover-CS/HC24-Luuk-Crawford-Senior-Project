package edu.hanover.hc24_luuk_crawford_senior_project.services

import android.content.ContentValues
import android.util.Log
import com.google.firebase.firestore.QuerySnapshot
import edu.hanover.hc24_luuk_crawford_senior_project.data.MenuItem
import edu.hanover.hc24_luuk_crawford_senior_project.services.createItemFrom

fun listDocumentItemsIn(document: QuerySnapshot, list: MutableList<MenuItem>): MutableList<MenuItem> {

    Log.d(ContentValues.TAG, "TEST ${document} is food document")
    for (foodOffering in document) {
        val food = foodOffering.data
        Log.d(ContentValues.TAG, "TEST ${food} is food id${food["id"]}")
        val nextItem = createItemFrom(food)
        list.add(nextItem)
    }
    return list
}