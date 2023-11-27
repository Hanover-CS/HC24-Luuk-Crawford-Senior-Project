package edu.hanover.hc24_luuk_crawford_senior_project.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.zybooks.hc24_luuk_crawford_senior_project.R
import edu.hanover.hc24_luuk_crawford_senior_project.data.MenuItem

/**
 * Loads imange and name of input menuItem
 * @param menuItem MenuItem to display
 */
@Composable
fun imageAndTextFor(menuItem: MenuItem) {
    Row {
        Image(
            painter = rememberAsyncImagePainter(menuItem.imageLink),
            contentDescription = stringResource(R.string.lostImage),
            modifier = Modifier.size(80.dp)
        )
        Column(Modifier.padding(10.dp)) {
            Text(text = menuItem.name,
                style = TextStyle(fontSize = 30.sp),
                fontWeight = FontWeight.Bold,
            )
            Text(text = menuItem.price,
                style = TextStyle(fontSize = 20.sp),
            )
        }
    }
}