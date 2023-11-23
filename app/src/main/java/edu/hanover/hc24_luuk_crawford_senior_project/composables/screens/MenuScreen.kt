package edu.hanover.hc24_luuk_crawford_senior_project.composables.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.zybooks.hc24_luuk_crawford_senior_project.R
import edu.hanover.hc24_luuk_crawford_senior_project.composables.hcLogoText
import edu.hanover.hc24_luuk_crawford_senior_project.composables.imageAndTextFor
import edu.hanover.hc24_luuk_crawford_senior_project.data.MenuData
import edu.hanover.hc24_luuk_crawford_senior_project.data.currentOrder.CurrentOrderManager.setOrderItemID
import edu.hanover.hc24_luuk_crawford_senior_project.services.downloadMenuFirebase
/**
 * MenuScreen to view column of food options in menu (menuItemList).
 * @param onNavigateToToppings needs function to change page to Toppings.
 */
@Composable
fun MenuScreen(onNavigateToToppings: () -> Unit) {
    hcLogoText()

    if (MenuData.get().menuItemList.isEmpty()){
        Column {
            Spacer(modifier = Modifier.height(70.dp))
            Text(text = stringResource(R.string.menu_could_not_be_found))
        }
    }
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
