package edu.hanover.hc24_luuk_crawford_senior_project.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.hanover.hc24_luuk_crawford_senior_project.data.currentOrder.CurrentOrderManager.getUserName
import edu.hanover.hc24_luuk_crawford_senior_project.data.currentOrder.CurrentOrderManager.setUsersName

/**
 * box to for user to input their name.
 * sets name to the current order.
 */
@Composable
fun inputUserName() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        var text by remember { mutableStateOf("") }
        text = getUserName()
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
                setUsersName(text)
            },
            label = { Text("Your name:", fontSize = 25.sp) },
            textStyle = TextStyle(fontSize = 30.sp),
            modifier = Modifier.testTag("inputUserName")
        )
    }
}