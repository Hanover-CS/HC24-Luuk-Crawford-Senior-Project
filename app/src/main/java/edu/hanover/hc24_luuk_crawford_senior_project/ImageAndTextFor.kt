package edu.hanover.hc24_luuk_crawford_senior_project

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.zybooks.hc24_luuk_crawford_senior_project.R

@Composable
fun imageAndTextFor(menuItem: MenuItem) {
    Row {
        Image(
            painter = rememberAsyncImagePainter(menuItem.imageLink),
            contentDescription = stringResource(R.string.lostImage),
            modifier = Modifier.size(70.dp)
        )
        Column(Modifier.padding(10.dp)) {
            Text(text = menuItem.name)
            Text(text = "test")
        }
    }
}