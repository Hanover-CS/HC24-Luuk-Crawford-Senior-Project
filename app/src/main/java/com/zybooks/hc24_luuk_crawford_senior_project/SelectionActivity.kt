package com.zybooks.hc24_luuk_crawford_senior_project

import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.zybooks.hc24_luuk_crawford_senior_project.databinding.ActivitySelectionBinding
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
class SelectionActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySelectionBinding
    private lateinit var menuArrayList : ArrayList<MenuOffer>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySelectionBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val menujson = """[{
            "id": 0,
            "name": "Hamburger",
            "customization": "Burger"
        }, {
            "id": 1,
            "name": "Veggie Burger",
            "customization": "Burger"
        }, {
            "id": 2,
            "name": "Quesadilla",
            "customization": "Quesadilla"
        }]"""
        data class MenuItem(val id: Int, val name: String, val customization: String)
        val mapper = ObjectMapper().registerKotlinModule()

        val menuItems: List<MenuItem> = mapper.readValue(menujson)
        val itemNames = menuItems.map { it.name }
        val itemCustomization = menuItems.map {it.customization}




        //figure out values
        val imageId = intArrayOf(R.drawable.phototest,R.drawable.ic_launcher_background,R.drawable.ic_launcher_foreground)



        val sideInfos = arrayListOf<String>()
        val itemPrices = arrayListOf<String>()




//        setContentView(R.layout.activity_selection)


        //add items to menu list
        menuArrayList = ArrayList()

        for (i in itemNames.indices){

            when(itemCustomization[i]){
                "Burger" ->{
                    sideInfos.add("+ Choice of side")
                    itemPrices.add("Mealswipe")
                }
                "Quesadilla" ->{
                    sideInfos.add("")
                    itemPrices.add("Mealswipe + 3")
                }
                else -> {
                    sideInfos.add("unknown sideInfo")
                    itemPrices.add("unknown itemPrice")
                }
            }


            val item = MenuOffer(itemNames[i], sideInfos[i], itemPrices[i], imageId[i])
            menuArrayList.add(item)
        }

        binding.listview.isClickable = true
        binding.listview.adapter = MenuAdapter(this,menuArrayList) //14:30
        binding.listview.setOnItemClickListener { parent, view, position, id ->

            val image = imageId[position]
            val name = itemNames[position]
            val side = sideInfos[position]
            val price = itemPrices[position]


            val i = Intent(this, CustomizationActivity::class.java)
            i.putExtra("name", name)
            i.putExtra("image", image)
            i.putExtra("side", side)
            i.putExtra("price", price)

            startActivity(i)

        }

    }


}