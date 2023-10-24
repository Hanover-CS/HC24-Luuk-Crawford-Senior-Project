package edu.hanover.hc24_luuk_crawford_senior_project.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.zybooks.hc24_luuk_crawford_senior_project.R
import edu.hanover.hc24_luuk_crawford_senior_project.data.MenuData
import edu.hanover.hc24_luuk_crawford_senior_project.data.MenuItem

//start order, ID,
@Composable
fun ToppingsScreen(item: MenuItem) {
    //val itemCustomization = customizationOptions[item.customizationType]
    val itemCustomization = MenuData.get().customizationOptions[item.customizationType]
    Column {
        Text(text = "This is the toppings screen!")
        imageAndTextFor(menuItem = item)

        Text(text = stringResource(R.string.toppings))

        if (itemCustomization != null) {
            createCheckboxForEach(itemCustomization.toppings)
        } else throw NullPointerException("Expression 'itemCustomization' must not be null")

        Text(text = stringResource(R.string.sides))
        createCheckboxForEach(itemCustomization.sides)

    }
}