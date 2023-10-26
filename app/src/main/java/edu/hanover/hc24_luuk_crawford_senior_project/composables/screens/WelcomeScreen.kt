package edu.hanover.hc24_luuk_crawford_senior_project.composables.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.hanover.hc24_luuk_crawford_senior_project.activities.getUserName
import edu.hanover.hc24_luuk_crawford_senior_project.activities.setUsersName
import edu.hanover.hc24_luuk_crawford_senior_project.composables.beginOrderButton
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

@Composable
fun inputUserName() {
    var text by remember { mutableStateOf("") }
    text = getUserName()
    OutlinedTextField(
        value = text,
        onValueChange = { text = it
                        setUsersName(text)
        },
        label = { Text("Your name:") }
    )
}
