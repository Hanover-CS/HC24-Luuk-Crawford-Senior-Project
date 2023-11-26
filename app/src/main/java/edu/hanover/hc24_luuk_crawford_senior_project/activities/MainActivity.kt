package edu.hanover.hc24_luuk_crawford_senior_project.activities


// ...

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import edu.hanover.hc24_luuk_crawford_senior_project.composables.AppNavHost
import edu.hanover.hc24_luuk_crawford_senior_project.services.downloadMenuFirebase

/**
 *On app launch
 *Starts AppNavHost (the composable that changes pages)
 */
class MainActivity : ComponentActivity() {


    /**
     * Launches AppNavHost and downloads menu from database
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavHost()
        }
        downloadMenuFirebase()
    }
}

