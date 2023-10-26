package edu.hanover.hc24_luuk_crawford_senior_project.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zybooks.hc24_luuk_crawford_senior_project.R

@Composable
fun beginOrderButton(onNavigateToMenu: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = { onNavigateToMenu() },
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier.testTag("beginOrderButton")
            ) {
            Text(
                text = stringResource(id = R.string.beginOrder),
                fontSize = 40.sp,
                color = Color.White, textAlign = TextAlign.Center
            )
        }
    }
}