package edu.hanover.hc24_luuk_crawford_senior_project.composables.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp

import edu.hanover.hc24_luuk_crawford_senior_project.composables.beginOrderButton
import edu.hanover.hc24_luuk_crawford_senior_project.composables.inputUserName
import edu.hanover.hc24_luuk_crawford_senior_project.composables.locationInfoLogo

/*
* This is the starting screen.
* - shows underground and school name and such
* - button to navigate to menu
* */
@Composable
fun WelcomeScreen(onNavigateToMenu: () -> Unit) {
    Column {
        locationInfoLogo()
        Spacer(modifier = Modifier.height(20.dp))
        inputUserName()
        //inputID()
        beginOrderButton(onNavigateToMenu)
    }
}

