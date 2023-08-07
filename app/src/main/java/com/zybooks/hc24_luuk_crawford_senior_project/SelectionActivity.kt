package com.zybooks.hc24_luuk_crawford_senior_project

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater

import androidx.appcompat.app.AppCompatActivity
import com.zybooks.hc24_luuk_crawford_senior_project.databinding.ActivityMainBinding
import com.zybooks.hc24_luuk_crawford_senior_project.databinding.ActivitySelectionBinding

class SelectionActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySelectionBinding
    private lateinit var menuArrayList : ArrayList<MenuOffer>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySelectionBinding.inflate(layoutInflater)

        setContentView(binding.root)


        val imageId = intArrayOf(R.drawable.phototest,R.drawable.ic_launcher_background,R.drawable.ic_launcher_foreground)

        val itemNames = arrayOf("name1","Name2","nameThree")

        val sideInfos = arrayOf("sideInfo1", "", "SideInfo3")

        val itemPrices = arrayOf("Mealswipe", "Mealswipe", "Mealswipe + 3")

        val itemExtraOptions = arrayOf("abc","adf","bcd")



//        setContentView(R.layout.activity_selection)


        menuArrayList = ArrayList()

        for (i in itemNames.indices){
            val item = MenuOffer(itemNames[i], sideInfos[i], itemPrices[i], imageId[i], itemExtraOptions[i])
            menuArrayList.add(item)
        }

        binding.listview.isClickable = true
        binding.listview.adapter = MenuAdapter(this,menuArrayList) //14:30
        binding.listview.setOnItemClickListener { parent, view, position, id ->

            val image = imageId[position]
            val name = itemNames[position]
            val side = sideInfos[position]
            val price = itemPrices[position]
            val extraOptions = itemExtraOptions[position]

            val i = Intent(this, SidesActivity::class.java)
            i.putExtra("name", name)
            i.putExtra("image", image)
            i.putExtra("side", side)
            i.putExtra("price", price)
            i.putExtra("extraOptions", extraOptions)
            startActivity(i)

        }

    }


}