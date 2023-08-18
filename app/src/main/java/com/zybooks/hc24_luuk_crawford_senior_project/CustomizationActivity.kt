package com.zybooks.hc24_luuk_crawford_senior_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.zybooks.hc24_luuk_crawford_senior_project.databinding.ActivitySidesBinding
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest


class CustomizationActivity : AppCompatActivity(){
    private lateinit var binding: ActivitySidesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val itemName = intent.getStringExtra("name")
        loadLayout(itemName)


    }

    private fun loadLayout(itemName: String?) {
        when (itemName) {
            "Burger" -> {
                this.setContentView(R.layout.regular_customization)
            }

            "Quesadilla" -> {
                this.setContentView(R.layout.quesadilla_customization)
            }

            else -> {
                this.setContentView(R.layout.regular_customization)
            }
        }
        fillItemInfo(itemName)
        setupBackButton()

/*
        val client = createSupabaseClient(
            supabaseUrl = "https://zvozxhyetcfobozzlvkv.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inp2b3p4aHlldGNmb2Jvenpsdmt2Iiwicm9sZSI6ImFub24iLCJpYXQiOjE2ODUzOTY3OTIsImV4cCI6MjAwMDk3Mjc5Mn0.d1cHG_ZusA3VEfs8AtmXFjIJoww3fO6hQz5sKlx0Blg"
        ) {
            install(Postgrest)
        }

        client.postgrest["countries"].update(
            {
                set("customer", "test1")
            }
        ) {

            eq("id", 1)
        }*/






    }

    private fun fillItemInfo(name: String?) {
        val itemName = findViewById<TextView>(R.id.itemName)
        itemName.text = name

        val sideText = findViewById<TextView>(R.id.foodSideInformation)
        sideText.text = intent.getStringExtra("side")

        val costText = findViewById<TextView>(R.id.foodPrice)
        costText.text = intent.getStringExtra("price")

        val foodPicture = findViewById<ImageView>(R.id.foodPhoto)
        val image = intent.getIntExtra("image", R.drawable.ic_launcher_background)
        foodPicture.setImageResource(image)
    }

    private fun setupBackButton() {
        val buttonClick = findViewById<Button>(R.id.backToList)
        buttonClick.setOnClickListener {
            val intent = Intent(this, SelectionActivity::class.java)
            startActivity(intent)
        }
    }



}
