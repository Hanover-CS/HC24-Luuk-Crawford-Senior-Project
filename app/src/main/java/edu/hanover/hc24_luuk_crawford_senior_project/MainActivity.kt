package edu.hanover.hc24_luuk_crawford_senior_project


// ...

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.hanover.hc24_luuk_crawford_senior_project.composables.screens.MenuScreen
import edu.hanover.hc24_luuk_crawford_senior_project.composables.screens.ToppingsScreen
import edu.hanover.hc24_luuk_crawford_senior_project.composables.screens.WelcomeScreen
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
        } else {
            val menuInfo = downloadMenuLocal()
            MenuData.set(menuInfo.menuItemList, menuInfo.customizationOptions)
            //myMenuList = menuInfo.menuItemList
            //customizationOptions = menuInfo.customizationOptions
        }

    }



}

/*
Nav host is the composable manager
- set start location
- handles which composable to load when triggered
 */
@Composable
fun AppNavHost(
    startDestination: String = Destination.welcomeScreen.name,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        //activated when button clicked.
        val onNavigateToMenuFunction = { navController.navigate(Destination.menuScreen.name) }
        val onNavigateToToppingsFunction =
            { navController.navigate(Destination.toppingsScreen.name) }

        //composable(destination.place.name){ Destination(navigateButtonInstruction)}
        composable(Destination.welcomeScreen.name) { WelcomeScreen(onNavigateToMenuFunction) }

        composable(Destination.menuScreen.name) { MenuScreen(onNavigateToToppingsFunction) }

        composable(Destination.toppingsScreen.name) { ToppingsScreen(itemToLoad) }

    }

}




