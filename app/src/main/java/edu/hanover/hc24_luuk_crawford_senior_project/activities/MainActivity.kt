package edu.hanover.hc24_luuk_crawford_senior_project.activities


// ...

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberAsyncImagePainter
import edu.hanover.hc24_luuk_crawford_senior_project.composables.AppNavHost
import edu.hanover.hc24_luuk_crawford_senior_project.services.downloadMenuFirebase

/**
 *On app launch
 *Starts AppNavHost (the composable that changes pages)
 */
class MainActivity : ComponentActivity() {


    /**
     * Launches AppNavHost and downloads menu from database
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            setBackground()
            AppNavHost()
        }
        downloadMenuFirebase()
    }

    @Composable
    private fun setBackground() {
        Image(
            painter = rememberAsyncImagePainter(model = "https://i.imgur.com/lbXJzQa.jpg"),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    alpha = 0.1f
                })
    }
}

