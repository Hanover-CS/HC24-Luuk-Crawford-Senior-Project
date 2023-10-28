package edu.hanover.hc24_luuk_crawford_senior_project.composables.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import edu.hanover.hc24_luuk_crawford_senior_project.composables.hcLogoText
import edu.hanover.hc24_luuk_crawford_senior_project.composables.imageAndTextFor
import edu.hanover.hc24_luuk_crawford_senior_project.data.MenuData
import edu.hanover.hc24_luuk_crawford_senior_project.activities.setOrderItemID

@Composable
fun MenuScreen(onNavigateToToppings: () -> Unit) {
    hcLogoText()

    LazyColumn {
        item {
            Spacer(modifier = Modifier.height(40.dp))
        }
        for (menuItem in MenuData.get().menuItemList) {

            item {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .testTag(menuItem.name)
                        .clickable {
                            setOrderItemID(menuItem.id)
                            onNavigateToToppings()
                        })
                {
                    imageAndTextFor(menuItem)

                }
            }
        }
    }
}