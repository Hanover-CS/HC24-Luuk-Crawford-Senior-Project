package edu.hanover.hc24_luuk_crawford_senior_project.composables

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import edu.hanover.hc24_luuk_crawford_senior_project.CheckButtonFor

@Composable
@OptIn(ExperimentalLayoutApi::class)
fun createCheckboxForEach(itemList: List<String>) {
    var checkedItems by remember { mutableStateOf(emptyList<String>()) }
    FlowRow {
        for (item in itemList) {
            CheckButtonFor(selectionName = item)
        }
    }
}