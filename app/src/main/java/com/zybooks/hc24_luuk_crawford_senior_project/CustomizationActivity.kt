package com.zybooks.hc24_luuk_crawford_senior_project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColor
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
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
        setupConfirmButton()
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

    private fun setupConfirmButton(){
        val buttonClick = findViewById<Button>(R.id.submitOrderButton)
        buttonClick.setOnClickListener{

            val dataToSave = HashMap<String, String>()

            val itemName = findViewById<TextView>(R.id.itemName).text.toString()


            dataToSave.put("item", itemName)
            dataToSave.put("side", "Tater Tots")
            dataToSave.put("toppings", "LTO")


            FirebaseFirestore.getInstance().document("sampleData/order001").set(dataToSave)

        }
    }

}
