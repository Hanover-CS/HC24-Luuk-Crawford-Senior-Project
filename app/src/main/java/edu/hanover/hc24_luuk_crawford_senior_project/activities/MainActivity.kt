package edu.hanover.hc24_luuk_crawford_senior_project.activities


// ...

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import edu.hanover.hc24_luuk_crawford_senior_project.composables.AppNavHost
import edu.hanover.hc24_luuk_crawford_senior_project.data.MenuData
import edu.hanover.hc24_luuk_crawford_senior_project.data.MenuItem
import edu.hanover.hc24_luuk_crawford_senior_project.services.downloadMenuFirebase
import edu.hanover.hc24_luuk_crawford_senior_project.services.downloadMenuLocal

//This setups loading to make my composable's work.
//- Starts AppNavHost

//var myMenuList = mutableListOf<MenuItem>()//
var itemToLoad = MenuItem()
//var customizationOptions = mutableMapOf<String, Customization>()//


var mySelections = mutableListOf<String>()

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavHost()
        }

        val firebaseIsEnabled = false
        if (firebaseIsEnabled) {
            val menuInfo = downloadMenuFirebase()
            MenuData.set(menuInfo.menuItemList, menuInfo.customizationOptions)
        } else {
            val menuInfo = downloadMenuLocal()
            MenuData.set(menuInfo.menuItemList, menuInfo.customizationOptions)
        }

    }



}




