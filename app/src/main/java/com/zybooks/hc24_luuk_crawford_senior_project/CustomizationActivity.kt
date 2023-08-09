package com.zybooks.hc24_luuk_crawford_senior_project

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.zybooks.hc24_luuk_crawford_senior_project.databinding.ActivitySidesBinding

class CustomizationActivity : AppCompatActivity(){
    private lateinit var binding: ActivitySidesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySidesBinding.inflate(layoutInflater)

        //setContentView(binding.root)

        val name = intent.getStringExtra("name")
        val image = intent.getIntExtra("image", R.drawable.ic_launcher_background)
        //val image = intent.getStringExtra("image")
        val side = intent.getStringExtra("side")
        val price = intent.getStringExtra("price")
        val extraOptions = intent.getStringExtra("extraOptions")

        this.setContentView(R.layout.regular_customization)
        //this.setContentView(R.layout.list_item)


        val itemName = findViewById<TextView>(R.id.itemName)
        itemName.text = name

        val sideText = findViewById<TextView>(R.id.foodSideInformation)
        sideText.text = side

        val costText = findViewById<TextView>(R.id.foodPrice)
        costText.text = price

        val foodPicture = findViewById<ImageView>(R.id.foodPhoto)
        foodPicture.setImageResource(image)



        //binding.name.text = name
        //binding.foodPhoto.setImageResource(image)
        //binding.debug.text = extraOptions




        //binding.foodSideInformation.text = side
        //binding.foodPrice.text = price


        if (extraOptions != null) {
            if (extraOptions.contains('a')){
                //binding.debug.text = extraOptions


            }else{
                //binding.debug.text = "not contain a"
            }
        }else{
            //binding.debug.text = "ERROR: null extraOptions"
        }



    }

}
