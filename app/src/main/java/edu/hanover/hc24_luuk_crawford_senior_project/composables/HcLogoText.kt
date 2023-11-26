package edu.hanover.hc24_luuk_crawford_senior_project.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.zybooks.hc24_luuk_crawford_senior_project.R

/**
 * College name in big text.
 */
@Composable
fun hcLogoText() {
    Text(
        text = stringResource(id = R.string.collegeNameCaps),
        fontSize = 35.sp,
        fontWeight = FontWeight.Bold,

        textAlign = TextAlign.Center,
        color = colorResource(id = R.color.hanoverWebRed),
        modifier = Modifier.testTag("collegeNameText")
    )
}