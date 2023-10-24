package edu.hanover.hc24_luuk_crawford_senior_project.services

import android.content.ContentValues
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import edu.hanover.hc24_luuk_crawford_senior_project.data.Customization
import edu.hanover.hc24_luuk_crawford_senior_project.data.MenuData
import edu.hanover.hc24_luuk_crawford_senior_project.data.MenuItem

fun downloadMenuFirebase(): MenuData {




    val menuList = downloadItems()
    val customizationMap = downloadToppings()

    val downloadedMenuData = MenuData(
        menuItemList = menuList,//mutableListOf<MenuItem>()
        customizationOptions = customizationMap//mutableMapOf<String, Customization>()
    )
    return downloadedMenuData

}

private fun downloadToppings(): MutableMap<String, Customization> {
    //customizationOptions.clear()
    val customizationMap = mutableMapOf<String, Customization>()

    val db = FirebaseFirestore.getInstance()
    val docRef = db.collection("customization")
    docRef.get()
        .addOnSuccessListener { document ->
            if (document != null) {
                for (itemType in document) {
                    val customizationData = itemType.data

                    val sides = customizationData["sides"] as? List<String> ?: emptyList()
                    val toppings = customizationData["toppings"] as? List<String> ?: emptyList()

                    val customization = Customization(sides, toppings)

                    customizationMap["${itemType.id}"] = customization
                }
            } else {
                Log.d(ContentValues.TAG, "No such document")
            }
        }
        .addOnFailureListener { exception ->
            Log.d(ContentValues.TAG, "get failed with ", exception)
        }
    return customizationMap
}

private fun downloadItems(): MutableList<MenuItem> {
    //myMenuList.clear()
    var myMenuList = mutableListOf<MenuItem>()
    val db = FirebaseFirestore.getInstance()
    val docRef = db.collection("menuContent")
    docRef.get()
        .addOnSuccessListener { document ->
            if (document != null) {
                myMenuList = listDocumentItemsIn(document, myMenuList)
            } else {
                Log.d(ContentValues.TAG, "No such document")
            }
        }
        .addOnFailureListener { exception ->
            Log.d(ContentValues.TAG, "get failed with ", exception)
        }
    return myMenuList
}