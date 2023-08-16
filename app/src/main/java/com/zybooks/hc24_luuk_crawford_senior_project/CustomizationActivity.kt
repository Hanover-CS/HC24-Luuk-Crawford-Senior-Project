package com.zybooks.hc24_luuk_crawford_senior_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.zybooks.hc24_luuk_crawford_senior_project.databinding.ActivitySidesBinding

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
