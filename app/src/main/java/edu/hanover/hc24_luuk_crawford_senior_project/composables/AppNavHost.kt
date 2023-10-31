package edu.hanover.hc24_luuk_crawford_senior_project.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.hanover.hc24_luuk_crawford_senior_project.data.currentOrder.CurrentOrderManager.getCurrentUserOrder
import edu.hanover.hc24_luuk_crawford_senior_project.data.Destination
import edu.hanover.hc24_luuk_crawford_senior_project.composables.screens.MenuScreen
import edu.hanover.hc24_luuk_crawford_senior_project.composables.screens.OrdersScreen
import edu.hanover.hc24_luuk_crawford_senior_project.composables.screens.ToppingsScreen
import edu.hanover.hc24_luuk_crawford_senior_project.composables.screens.WelcomeScreen


/**
 * Nav host is the composable manager
 * set start location
 * handles which composable to load when triggered
 * It is set up like this to prevent giving navController itself to everything.
 * @param startDestination Which composable to start at (string). Defaults to WelcomeScreen.
 * @param navController handles navigation navController.navigate (Destination name). Defaults rememberNavController()
 *///TODO: remove modifier?
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
        val onNavigateToOrdersScreen =
            { navController.navigate(Destination.ordersScreen.name) }

        composable(Destination.welcomeScreen.name) { WelcomeScreen(onNavigateToMenuFunction) }

        composable(Destination.menuScreen.name) { MenuScreen(onNavigateToToppingsFunction) }

        composable(Destination.toppingsScreen.name) { ToppingsScreen( onNavigateToOrdersScreen,getCurrentUserOrder()) }

        composable(Destination.ordersScreen.name){ OrdersScreen()}
    }
}