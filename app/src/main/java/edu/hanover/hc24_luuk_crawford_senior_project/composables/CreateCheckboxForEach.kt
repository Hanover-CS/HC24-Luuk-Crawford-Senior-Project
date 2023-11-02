package edu.hanover.hc24_luuk_crawford_senior_project.composables

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

/**
 * creates checkbox for the list of a certain category.
 * @param itemList the list to turn into checkboxes
 * @param customizationCategory category list belongs to
 */
@Composable
@OptIn(ExperimentalLayoutApi::class)
fun createCheckboxForEach(itemList: List<String>, customizationCategory: String) {
    FlowRow {
        for (item in itemList) {
            CheckButtonFor(selectionName = item, customizationCategory)
        }
    }
}