package com.zybooks.hc24_luuk_crawford_senior_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase


import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text


// ...
import androidx.compose.runtime.Composable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MessageCard("Android")
        }
    }
}
@Composable
fun MessageCard(name: String) {
    Text(text = "Hello $name!")
}



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
