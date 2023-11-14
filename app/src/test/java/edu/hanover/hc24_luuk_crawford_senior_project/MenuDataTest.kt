package edu.hanover.hc24_luuk_crawford_senior_project

import edu.hanover.hc24_luuk_crawford_senior_project.data.Customization
import edu.hanover.hc24_luuk_crawford_senior_project.data.MenuData
import edu.hanover.hc24_luuk_crawford_senior_project.data.MenuItem
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class MenuDataTest {

    @Test
    fun getEmptyMenuData(){
        MenuData.clearMenuContents()
        val emptyMenuData = MenuData(mutableListOf<MenuItem>(), mutableMapOf<String, Customization>(),mutableMapOf<Long, MenuItem>())

        val menuData = MenuData.get()
        assertEquals(emptyMenuData,menuData)
    }

    @Test
    fun addMenuItem(){

        val emptyMenuData = MenuData(mutableListOf<MenuItem>(), mutableMapOf<String, Customization>(),mutableMapOf<Long, MenuItem>())
        assertEquals(emptyMenuData,MenuData.get())
        val exampleItem = MenuItem()
        MenuData.addMenuItem(exampleItem)
        assertNotEquals(emptyMenuData,MenuData.get())
    }

    @Test
    fun clearMenu(){
        MenuData.addMenuItem(MenuItem())
        MenuData.addMenuOption("item", Customization(mutableListOf("side1"), mutableListOf("topping1")))
        MenuData.clearMenuContents()

        val emptyMenuData = MenuData(mutableListOf<MenuItem>(), mutableMapOf<String, Customization>(),mutableMapOf<Long, MenuItem>())
        assertEquals(emptyMenuData,MenuData.get())
    }

    @Test
    fun addMenuOptionTest(){
        MenuData.clearMenuContents()
        val itemName = "BLT7"
        val customization = Customization(mutableListOf("side1","side2"), mutableListOf("topping1","topping2"))

        val expectedMap = mutableMapOf<String,Customization>()
        assertEquals(expectedMap,MenuData.get().customizationOptions)

        MenuData.addMenuOption(itemName, customization)
        expectedMap[itemName] = customization
        assertEquals(expectedMap, MenuData.get().customizationOptions)

        val bltToppings = MenuData.get().customizationOptions.get("BLT7")!!.toppings
        val bltSides = MenuData.get().customizationOptions.get("BLT7")!!.sides
        assertEquals(mutableListOf("topping1","topping2"), bltToppings)
        assertEquals(mutableListOf("side1","side2"), bltSides)
    }

}