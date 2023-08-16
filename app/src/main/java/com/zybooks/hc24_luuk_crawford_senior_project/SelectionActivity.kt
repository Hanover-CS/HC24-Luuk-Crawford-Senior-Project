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
        val itemCustomizationType = menuItems.map {it.customization}

        val imageId = intArrayOf(R.drawable.phototest,R.drawable.ic_launcher_background,R.drawable.ic_launcher_foreground)
        val sideInfos = arrayListOf<String>()
        val itemPrices = arrayListOf<String>()

        fillMenuArrayListWith(itemNames, itemCustomizationType, sideInfos, itemPrices, imageId)

        binding.listview.isClickable = true
        binding.listview.adapter = MenuAdapter(this,menuArrayList) //14:30
        binding.listview.setOnItemClickListener { parent, view, position, id ->
            startCustomizationActivityWithIntent(imageId, position, itemNames, sideInfos, itemPrices)
        }

    }

    private fun startCustomizationActivityWithIntent(
        imageId: IntArray,
        position: Int,
        itemNames: List<String>,
        sideInfos: ArrayList<String>,
        itemPrices: ArrayList<String>
    ) {

        val i = Intent(this, CustomizationActivity::class.java)
        i.putExtra("name", itemNames[position])
        i.putExtra("image", imageId[position])
        i.putExtra("side", sideInfos[position])
        i.putExtra("price", itemPrices[position])

        startActivity(i)
    }

    private fun fillMenuArrayListWith(
        itemNames: List<String>,
        itemCustomizationType: List<String>,
        sideInfos: ArrayList<String>,
        itemPrices: ArrayList<String>,
        imageId: IntArray
    ) {
        menuArrayList = ArrayList()
        for (i in itemNames.indices) {
            addInfoToListsBasedOnType(itemCustomizationType, i, sideInfos, itemPrices)
            val item = MenuOffer(itemNames[i], sideInfos[i], itemPrices[i], imageId[i])
            menuArrayList.add(item)
        }
    }

    private fun addInfoToListsBasedOnType(
        customizationType: List<String>,
        i: Int,
        sideInfos: ArrayList<String>,
        itemPrices: ArrayList<String>
    ) {
        when (customizationType[i]) {
            "Burger" -> {
                sideInfos.add(getString(R.string.choiceOfSide))
                itemPrices.add(getString(R.string.mealswipe))

            }

            "Quesadilla" -> {
                sideInfos.add(getString(R.string.nothing))
                itemPrices.add(getString(R.string.mealswipe3))
            }

            else -> {
                sideInfos.add(getString(R.string.unknown))
                itemPrices.add(getString(R.string.unknown))
            }
        }
    }


}