package edu.hanover.hc24_luuk_crawford_senior_project.activities


// ...

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import edu.hanover.hc24_luuk_crawford_senior_project.composables.AppNavHost
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

