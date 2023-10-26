package edu.hanover.hc24_luuk_crawford_senior_project.composables

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zybooks.hc24_luuk_crawford_senior_project.R
import edu.hanover.hc24_luuk_crawford_senior_project.activities.getUserName



@Composable
fun beginOrderButton(onNavigateToMenu: () -> Unit) {
    val context = LocalContext.current
    val errorMessage = stringResource(R.string.please_enter_your_name)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                if (getUserName() != "" ){
                    onNavigateToMenu()
                } else{
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(context, errorMessage, duration)
                    toast.show()
                }
                      },
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