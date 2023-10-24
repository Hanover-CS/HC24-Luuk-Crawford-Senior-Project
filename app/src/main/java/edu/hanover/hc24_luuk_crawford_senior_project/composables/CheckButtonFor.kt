package edu.hanover.hc24_luuk_crawford_senior_project.composables

import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import edu.hanover.hc24_luuk_crawford_senior_project.mySelections

@Composable
fun CheckButtonFor(selectionName: String) {
    var checked by remember { mutableStateOf(false) }
    Row(
        Modifier
            .toggleable(
                value = checked,
                role = Role.Checkbox,
                onValueChange = {
                    checked = !checked
                }

            )
            .padding(10.dp)
            .wrapContentWidth()
            .width(100.dp)
            .background(color = Color(32))

    ) {
        Checkbox(checked = checked, { newValue ->
            checked = newValue
        })//onCheckedChange = null)
        //Text(text = if (checked) "Checked" else "Not Checked")
        if (checked) {
            mySelections.add(selectionName)
            Log.d(ContentValues.TAG, "added ${selectionName}")
            Log.d(ContentValues.TAG, mySelections.toString())
        } else {
            mySelections.remove(selectionName)
            Log.d(ContentValues.TAG, "removed ${selectionName}")
            Log.d(ContentValues.TAG, mySelections.toString())
        }
        Text(selectionName, Modifier.weight(1f))
    }
}