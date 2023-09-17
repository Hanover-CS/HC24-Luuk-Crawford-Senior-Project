package com.zybooks.hc24_luuk_crawford_senior_project

import android.os.Bundle


import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material.Button


// ...
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposableManager()
        }
    }
}
@Composable
fun ComposableManager(){
    val welcomeScreenEnabled by remember { mutableStateOf(true) }
    val foodOptionsScreenEnabled by remember { mutableStateOf(false) }

    if (welcomeScreenEnabled){ WelcomeScreen()}
    if (foodOptionsScreenEnabled){ FoodOptionsScreen() }
}

@Composable
fun MessageCard(name: String) {
    Text(text = "Hello $name!")
}
@Composable
fun WelcomeScreen() {

    Column {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.collegeNameCaps),
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,

            textAlign = TextAlign.Center,
            color = colorResource(id = R.color.hanoverWebRed)
        )
        Text(text = stringResource(id = R.string.underground),fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
        ShowMenuButton()

    }
}

@Composable
fun ShowMenuButton() {
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxHeight()


    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {

    Button(onClick = { /* do something on click */ },
    shape = RoundedCornerShape(18.dp),
    ) {
        Text(
            text = stringResource(id = R.string.beginOrder),
            fontSize = 40.sp,
            color = Color.White, textAlign = TextAlign.Center)
    }}
    }
}

@Composable
fun FoodOptionsScreen(){
    LazyColumn {//https://developer.android.com/jetpack/compose/lists#lazy
        // Add a single item
        item {
            Text(text = "First item")
        }

        // Add 5 items
        items(5) { index ->
            Text(text = "Item: $index")
        }

        // Add another single item
        item {
            Text(text = "Last item")
        }
    }
}


/* https://developer.android.com/jetpack/compose/layouts/basics
@Composable
fun ArtistAvatar(artist: Artist) {
    Box {
        Image(bitmap = artist.image, contentDescription = "Artist image")
        Icon(Icons.Filled.Check, contentDescription = "Check mark")
    }
}
*/


/*
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupButton()
        FirebaseApp.initializeApp(this)
        Firebase.analytics.logEvent("app_opened",null)
    }


    private fun setupButton() {
        val buttonClick = findViewById<Button>(R.id.toSelectionActivity)
        buttonClick.setOnClickListener {
            Firebase.analytics.logEvent("started_order",null)
            val intent = Intent(this, SelectionActivity::class.java)
            startActivity(intent)
        }
    }

}*/
